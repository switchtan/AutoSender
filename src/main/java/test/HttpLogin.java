package test;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

/**
 * 模拟登陆知乎
 */
public class HttpLogin {

    public static void main(String[] args) throws ParseException {
        String name = "399852658@qq.com";
        String password = "guavaguava00";

        // 全局请求设置
        RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
        // 创建cookie store的本地实例
        CookieStore cookieStore = new BasicCookieStore();
        // 创建HttpClient上下文
        HttpClientContext context = HttpClientContext.create();
        context.setCookieStore(cookieStore);

        // 创建一个HttpClient
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig)
                .setDefaultCookieStore(cookieStore).build();

        CloseableHttpResponse res = null;

        // 创建本地的HTTP内容
        try {
            try {
                // 创建一个get请求用来获取必要的Cookie，如_xsrf信息
                HttpGet get = new HttpGet("http://www.zhihu.com/");

                get.setHeader("ser-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.26 Safari/537.36");
                res = httpClient.execute(get, context);
                // 获取常用Cookie,包括_xsrf信息
                System.out.println("访问知乎首页后的获取的常规Cookie:===============");
                for (Cookie c : cookieStore.getCookies()) {
                    System.out.println(c.getName() + ": " + c.getValue());
                }
                res.close();

                // 构造post数据
                List<NameValuePair> valuePairs = new LinkedList<NameValuePair>();
                valuePairs.add(new BasicNameValuePair("username", name));
                valuePairs.add(new BasicNameValuePair("password", password));
                //valuePairs.add(new BasicNameValuePair("remember_me", "true"));
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(valuePairs, Consts.UTF_8);
                entity.setContentType("application/x-www-form-urlencoded");

                // 创建一个post请求
                HttpPost post = new HttpPost("https://www.zhihu.com/api/v3/oauth/sign_in");
                // 注入post数据
                post.setEntity(entity);
                res = httpClient.execute(post, context);

                // 打印响应信息，查看是否登陆是否成功
                System.out.println("打印响应信息===========");
                String contentLogin = EntityUtils.toString(res.getEntity());
                System.out.println(contentLogin);
                res.close();

                System.out.println("登陆成功后,新的Cookie:===============");
                for (Cookie c : context.getCookieStore().getCookies()) {
                    System.out.println(c.getName() + ": " + c.getValue());
                }
                String newcooke="_zap=5336ec65-67a2-497f-9dd0-eb2731b4a070; _xsrf=rHRAw7lGnxRPUj3ShMUe8N27Ywx7Si4P; d_c0='AGBiDaIexw6PTlRhhKkqNBUoI2Lu4LmvPm0=|1546660862'; tst=r; l_n_c=1; n_c=1; q_c1=e28e0d97749c40f6aa82ed423b6ee62e|1546671936000|1546671936000; __utma=51854390.1196564115.1546671941.1546671941.1546671941.1; __utmc=51854390; __utmz=51854390.1546671941.1.1.utmcsr=zhihu.com|utmccn=(referral)|utmcmd=referral|utmcct=/signup; __utmv=51854390.100-1|2=registration_date=20140111=1^3=entry_date=20140111=1; l_cap_id='NzYyMmQ5NTU0MzNiNDNmOGJjN2I4OWQ2NTRmZDg1ZDQ=|1546672605|60dff8161d0e0bbcc324f70a333576d638c08688'; r_cap_id='ODliMmM3NzJmYmQ4NGFlZGE1YWIzM2ZmYmJhZWIyMDI=|1546672605|5ed6708e2c4de8efee26fdb393e50182d7621c14'; cap_id='ZGQ2MjE4Y2UzMzEwNDVmZWE1OWNlM2FhMjkyNGViYTY=|1546672605|eb39740fa769a7a7b6d22140ef611cadc6ac40b9'; __gads=ID=47478137448f88d7:T=1546681086:S=ALNI_MZmJFgJKm9GtkqqEnd_iza91uaupQ; capsion_ticket='2|1:0|10:1546681304|14:capsion_ticket|44:NzlmMzlmN2UzZTcyNGQwZTk3ZTQ2NTRiN2JjMjIyN2E=|e4c21c5223b938bee310a0c90467291712273bec0a1c174a3734b593cda93de0'; z_c0='2|1:0|10:1546681320|4:z_c0|92:Mi4xV0dRdUFBQUFBQUFBWUdJTm9oN0hEaVlBQUFCZ0FsVk42TWtkWFFETkF1Z2xnc29oOWxQUTRGaDJCTUZfZ0pnNkpB|1941177578f426418c8f629c77d1caac6b71242185a03cd830579cad577611cd'; tgw_l7_route=7bacb9af7224ed68945ce419f4dea76d";
                String[] newcookes=newcooke.split("; ");

                for(String s:newcookes){
                    String[] ss=s.split("=");

                        BasicClientCookie c=new BasicClientCookie(ss[0],s.substring(ss[0].length()));
                        context.getCookieStore().addCookie(c);
                }

                System.out.println("伪造cookies,新的Cookie:===============");
                for (Cookie c : context.getCookieStore().getCookies()) {
                    System.out.println(c.getName() + ": " + c.getValue());
                }

                // 构造一个新的get请求，用来测试登录是否成功
                //HttpGet newGet = new HttpGet("http://www.zhihu.com/question/following");
                HttpGet newGet = new HttpGet("https://zhuanlan.zhihu.com/write");

                res = httpClient.execute(newGet, context);
                String content = EntityUtils.toString(res.getEntity());
                System.out.println("登陆成功后访问的页面===============");
                System.out.println(content);
                res.close();

            } finally {
                httpClient.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}