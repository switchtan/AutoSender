package sites;

import HttpUtil.HttpHelper;
import org.apache.commons.codec.digest.DigestUtils;
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
public class mtime_com {
    private String login_form_url       = "https://passport.mtime.com/member/signin";
    private String login_Action_url     = "https://passport.mtime.com/member/signinLogin";
    private String article_form_url     = "https://passport.mtime.com/member/signin";
    private String article_add_url      = "https://passport.mtime.com/member/signin";
    private String loginname            ="13326511991";
    private String loginpass            ="guavaguava00";
    public void setup() throws Exception {

        HttpHelper httpHelper=new HttpHelper();

        //获取请求登录的页面HTML
        String page=httpHelper.sentGet(login_form_url);

        //设置登录参数
        //List<NameValuePair> params = this.tranParams_login(page);

        //开始登录
        //httpHelper.sentPost(login_Action_url,params);

        //打开短信页面
        page=httpHelper.sentGet("http://vip.mtime.com/");


//        page=httpHelper.sentGet(article_form_url);
//        params = this.tranParams_login(page);
//        httpHelper.sentPost(article_add_url,params);

    }


    private List<NameValuePair> tranParams_login(String html) {
//        Document doc = Jsoup.parse(html);
//
//        //获取ITEye登录form
//        Element formEl = doc.getElementById("blog_form");
//        Elements inputs = formEl.getElementsByTag("input");
//
//        //解析ITEye登录form里面的登录名和登录密码
//        String token="";
//        for(Element el: inputs) {
//            String elName = el.attr("name");
//            String elValue = el.attr("value");
//            if(elName.equals("authenticity_token")){
//                token=elValue;
//
//            }
//           System.out.println(elName);
//        }
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("loginEmailText","13326511991"));
        String encodeStr= DigestUtils.md5Hex(this.loginpass);
        list.add(new BasicNameValuePair("loginPasswordText",encodeStr));
        list.add(new BasicNameValuePair("inputVcode",""));
        list.add(new BasicNameValuePair("isvcode","true"));
        list.add(new BasicNameValuePair("isAutoSign","false"));
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

            if(elName.equals("email")) {
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

