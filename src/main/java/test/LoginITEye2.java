package test;

import HttpUtil.HttpHelper;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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

        //设置登录参数
        LoginITEye2 iteye=new LoginITEye2();
        List<NameValuePair> params = iteye.tranParams(page, "gguava@gmail.com","guavaguava00");

        //开始登录
        httpHelper.sentPost(url,params);

        //打开短信页面
        //page=httpHelper.sentGet("https://my.iteye.com/myresume");


        page=httpHelper.sentGet("https://guava.iteye.com/admin/blogs/new");
        params = iteye.tranParams2(page);
        url="https://guava.iteye.com/admin/blogs";
        httpHelper.sentPost(url,params);

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

