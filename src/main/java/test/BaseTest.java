package test;



import sites.iteye_com.Base;

import java.io.IOException;



public class BaseTest {


    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("guava");
        Base base=new Base("https://www.iteye.com/login","https://www.iteye.com/","user_name","password"
        ,"button","gguava@gmail.com","guavaguava00");
        base.findPostUrl();
        base.postMessage(base.getPostUrl(),"ea的可选性 ","blog_title"
                ,"求均线角度是没有用的，均线都不是直线","editor_body"
                ,"blog_whole_category_id",3,"submit_button");
        Thread.sleep(50000);
        System.out.println("退出程序");
    }
}