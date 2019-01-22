package sites;

import HttpUtil.HttpHelper;
import Selenium.SeleniumUtil;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.io.IOException;
import java.util.Set;

public class BaseLogin {
    private HttpHelper httpHelper=new HttpHelper();
    private WebDriver driver;
    private String loginUrl="https://passport.mtime.com/member/signin";
    private String driverPath="D:\\BaiduNetdiskDownload\\chromedriver.exe";
    private String userTag="loginEmailText";
    private String passTag="loginPasswordText";
    private String btnTag="loginButton";
    private String userName="";
    private String password="";
    public BaseLogin(String login_url_, String userTag_, String passTag_, String btnTag_, String userName_, String password_) throws IOException {
        System.setProperty("webdriver.chrome.driver",driverPath);// chromedriver服务地址
        this.driver    = new ChromeDriver();
        driver.get(login_url_);



        //https://www.cnblogs.com/test-my/p/5942387.html
        //这篇文章不错 https://blog.csdn.net/qq_22003641/article/details/79137327
        WebElement email    = driver.findElement(By.id(userTag_));
        email.sendKeys(userName_);
        WebElement passw    = driver.findElement(By.id(passTag_));
        passw.sendKeys(password_);
        WebElement button   = driver.findElement(By.id(btnTag_));
        button.click();





    }

    public void postMessage(String url,String title,String content){
        System.out.println("gggggggggggggggggggggggggggggggggg");
        Set<Cookie> ss= driver.manage().getCookies();
        for(Cookie item:ss){
            System.out.println(item.getName()+":"+item.getValue());
        }
        ss= driver.manage().getCookies();


        //等待确认当前网址
        SeleniumUtil seleniumUtil=new SeleniumUtil();
        try {
            seleniumUtil.warpTimeOut(driver, 10,"http://www.mtime.com/");
        }catch (Exception e){
            e.printStackTrace();
        }
        BasicCookieStore basicCookieStore=seleniumUtil.CookieSet(ss);
        httpHelper.setCookie(basicCookieStore);
        //httpHelper.sentGet("http://service.mtime.com/Service/Twitter.msi?Ajax_CallBack=true&Ajax_CallBackType=Mtime.Service.Pages.TwitterService&Ajax_CallBackMethod=PostTweetCrossDomainByFlash&Ajax_CrossDomain=1&Ajax_RequestUrl=http%3A%2F%2Fmovie.mtime.com%2F242167%2F&t=201911920535539339&Ajax_CallBackArgument0=%E7%9A%84%E7%A1%AE%E6%98%AF%E5%BC%95%E5%85%A5%E6%B7%B1%E6%80%9D%E7%9A%84%E7%94%B5%E5%BD%B1&Ajax_CallBackArgument1=&Ajax_CallBackArgument2=0&Ajax_CallBackArgument3=1&Ajax_CallBackArgument4=242167&Ajax_CallBackArgument5=-1&Ajax_CallBackArgument6=0&Ajax_CallBackArgument7=0" );
        driver.get("http://movie.mtime.com/246986/");
        WebElement tex    = driver.findElement(By.id("tweetBoxUp"));
        tex.sendKeys("的确是让人深思的电影！");
        WebElement button2   = driver.findElement(By.id("tweetButtonUp"));
        button2.click();
    }
}
