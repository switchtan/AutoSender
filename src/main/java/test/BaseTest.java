package test;



import HttpUtil.HttpHelper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import sites.iteye_com.Base;

import java.io.IOException;



public class BaseTest {


    public static void main(String[] args) throws IOException, InterruptedException {
        HttpHelper httpHelper=new HttpHelper();
        String sitename = "iteye";
        String json = httpHelper.sentGet("http://ziran.copysignal.top/getAction.php?sitename="+sitename);
        JSONObject jsonArray = JSON.parseObject(json);
        JSONObject siterules = jsonArray.getJSONObject("siterule");
        JSONArray passwords = jsonArray.getJSONArray("password");
        //System.out.println(jsonArray.getJSONObject("siterule").getString("login_url"));
        if(jsonArray.size()>0) {
            JSONObject one_pass= passwords.getJSONObject(0);
            Base base = new Base(siterules.getString("login_url"), siterules.getString("login_succes_url")
                    , siterules.getString("login_user_tag")
                    , siterules.getString("login_pass_tag")
                    , siterules.getString("login_btn_tag")
                    , one_pass.getString("username")
                    , one_pass.getString("password"));
            base.findPostUrl(
                    siterules.getString("base_url_tag")
                    , siterules.getString("end_url"));
            base.postMessage(
                    base.getPostUrl(),
                    "ea的可选性 ",
                    siterules.getString("_post_title_tag"),
                    "求均线角度是没有用的，均线都不是直线",
                    siterules.getString("_post_content_tag"),
                    siterules.getString("_post_select_tag"),
                    3,
                    siterules.getString("_post_button_tag")
            );

        }
        Thread.sleep(50000);
        System.out.println("退出程序");
    }
}