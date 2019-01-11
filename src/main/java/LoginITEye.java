import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.List;

/**
 * 模仿登录iTeye
 * @author Li
 */
public class LoginITEye {
    private String cookies;
    private HttpClient http = new DefaultHttpClient();
    private final String USER_AGENT = "Mozilla/5.0";
    private String authenticity_token;
    public static void main(String[] args) throws Exception {
        System.setProperty("ht2.109tp.proxyHost", "127.0.0.1");
        System.setProperty("https.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "8888");
        System.setProperty("https.proxyPort", "8888");

        String url = "https://www.iteye.com/login";
        LoginITEye iteye = new LoginITEye();

        //打开cookie
        CookieHandler.setDefault(new CookieManager());

        //获取请求登录的页面HTML
        String page = iteye.sentGet(url);
        System.out.println(page);

        //设置登录参数
        List<NameValuePair> params = iteye.tranParams(page, "gguava@gmail.com","guavaguava00");

        //开始登录
        iteye.sendPost(url, params);




        String addnews = iteye.sentGet("https://guava.iteye.com/admin/blogs/new");
        System.out.println("---------- 请发表原创文章-----------");
        System.out.println(addnews);

        params = iteye.tranParams2(addnews);
        iteye.sendPost("https://guava.iteye.com/admin/blogs", params);


        String itEyePage = iteye.sentGet("https://my.iteye.com/messages");
        System.out.println("----------可以从itEyePage打印里面看到welcome欢迎***。表示登录成功.-----------");
        System.out.println(itEyePage);
    }

    /**
     * 获取iTeye登录页面
     * @param url
     * @return
     * @throws IOException
     * @throws ClientProtocolException
     */
    private String sentGet(String url) throws Exception {
        StringBuffer result = new StringBuffer();

        //设置请求信息
        HttpGet request = new HttpGet(url);
        request.setHeader("User-Agent", USER_AGENT);
        request.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        request.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-GB;q=0.6,en;q=0.4,zh-TW;q=0.2");

        //发送请求
        HttpResponse response = http.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();

        //statusCode==200表示请求成功.
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + statusCode);

        //读取返回内容解析成字符串
        BufferedReader br = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        String line = "";
        while((line = br.readLine()) != null) {
            result.append(line);
        }

        //设置cookie
        setCookies(response.getFirstHeader("Set-Cookie") == null ? "":
                response.getFirstHeader("Set-Cookie").toString());
//        Header[] hds=response.getAllHeaders();
//        System.out.println("Cookies:");
//        for(Header hitem:hds) {
//            System.out.println(hitem.toString());
//        }

        return result.toString();
    }
    /**
     * 开始模拟登录
     * @param url
     * @param params
     * @throws IOException
     * @throws ClientProtocolException
     */
    private void sendPost(String url, List<NameValuePair> params) throws Exception {
        HttpPost request = new HttpPost(url);

        request.setHeader("Host", "www.iteye.com");
        request.setHeader("User-Agent", USER_AGENT);

        request.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        request.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-GB;q=0.6,en;q=0.4,zh-TW;q=0.2");

        request.setHeader("Cookie", getCookies());
        request.setHeader("Connection", "keep-alive");

        //request.setHeader("Referer", "https://www.iteye.com/login");
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");

        request.setEntity(new UrlEncodedFormEntity(params));

        HttpResponse response = http.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();

        //statusCode==200表示请求成功.
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("send paramer: "+ params);
        System.out.println("Response Code : " + statusCode);
        //System.out.println(response.getFirstHeader("Set-Cookie").toString());
        BufferedReader br = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer sb = new StringBuffer();
        String line = "";
        while((line = br.readLine()) != null) {
            sb.append(line);
        }

        System.out.println(sb.toString());
    }

    /**
     * 把你的登录账号和密码设置到form里面去
     * @param html
     * @param name 你的iTeye登录名
     * @param word 你的iTeye登录密码
     * @return
     */
    private List<NameValuePair> tranParams(String html, String name, String word) {
        List<NameValuePair> list = new ArrayList<NameValuePair>();

        //转化HTML --> document
        Document doc = Jsoup.parse(html);

        //获取ITEye登录form
        Element formEl = doc.getElementById("login_form");
        Elements inputs = formEl.getElementsByTag("input");

        //解析ITEye登录form里面的登录名和登录密码
        for(Element el: inputs) {
            String elName = el.attr("name");
            String elValue = el.attr("value");

            if(elName.equals("name")) {
                elValue = name;
            }else if(elName.equals("password")) {
                elValue = word;
            }

            if(!elName.equals("button")) {
                if(elName.equals("authenticity_token"))authenticity_token=elValue;
                list.add(new BasicNameValuePair(elName, elValue));
            }
        }
        return list;
    }

    private List<NameValuePair> tranParams2(String html) {
        Document doc = Jsoup.parse(html);

        //获取ITEye登录form
        Element formEl = doc.getElementById("blog_form");
        Elements inputs = formEl.getElementsByTag("input");

        //解析ITEye登录form里面的登录名和登录密码
        String token="";
        for(Element el: inputs) {
            String elName = el.attr("name");
            String elValue = el.attr("value");
            if(elName.equals("authenticity_token")){
                token=elValue;
                System.out.println(token+":last token:"+this.authenticity_token);
            }
           System.out.println(elName);
        }
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("authenticity_token",token));
        list.add(new BasicNameValuePair("blog[blog_type]", "0"));
        list.add(new BasicNameValuePair("blog[whole_category_id]", "4"));
        list.add(new BasicNameValuePair("blog[title]", "java12即将到来 毕业生这么办"));
        list.add(new BasicNameValuePair("blog[category_list]", ""));
        list.add(new BasicNameValuePair("auto_save_id", ""));
        list.add(new BasicNameValuePair("blog[bbcode]", "true"));
        list.add(new BasicNameValuePair("blog[body]", "活活火火火火"));
        list.add(new BasicNameValuePair("blog[tag_list]", ""));
        list.add(new BasicNameValuePair("blog[diggable]", "0"));
        list.add(new BasicNameValuePair("topic[forum_id]", ""));
        list.add(new BasicNameValuePair("commit", "发布"));

        return list;
    }



    /**
     * @return the cookid
     */
    public String getCookies() {
        return cookies;
    }

    /**
     * @param cookies the cookid to set
     */
    public void setCookies(String cookies) {
        this.cookies = cookies;
    }

}

