import HttpUtil.HttpHelper;
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
public class LoginITEye2 {

    public static void main(String[] args) throws Exception {


        String url = "https://www.iteye.com/login";
        HttpHelper httpHelper=new HttpHelper();



        //获取请求登录的页面HTML
        String page=httpHelper.sentGet(url);
//        String page = iteye.sentGet(url);
//        System.out.println(page);

        //设置登录参数
        LoginITEye2 iteye=new LoginITEye2();
        List<NameValuePair> params = iteye.tranParams(page, "gguava@gmail.com","guavaguava00");

        //开始登录
//        iteye.sendPost(url, params);
        httpHelper.sentPost(url,params);

        //打开短信页面
        page=httpHelper.sentGet("https://my.iteye.com/myresume");


        page=httpHelper.sentGet("https://guava.iteye.com/admin/blogs/new");
        params = iteye.tranParams2(page);
        url="https://guava.iteye.com/admin/blogs";
        httpHelper.sentPost(url,params);
//        String addnews = iteye.sentGet("https://guava.iteye.com/admin/blogs/new");
//        System.out.println("---------- 请发表原创文章-----------");
//        System.out.println(addnews);
//
//        params = iteye.tranParams2(addnews);
//        iteye.sendPost("https://guava.iteye.com/admin/blogs", params);
//
//
//        String itEyePage = iteye.sentGet("https://my.iteye.com/messages");
//        System.out.println("----------可以从itEyePage打印里面看到welcome欢迎***。表示登录成功.-----------");
//        System.out.println(itEyePage);
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
                list.add(new BasicNameValuePair(elName, elValue));
            }
        }
        return list;
    }



}

