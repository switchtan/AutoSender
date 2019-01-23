package sites;

import HttpUtil.HttpHelper;
import Selenium.SeleniumUtil;
import org.apache.http.impl.client.BasicCookieStore;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.util.Set;

public class BaseSite {
    private HttpHelper httpHelper=new HttpHelper();
    protected WebDriver driver;
    private SeleniumUtil seleniumUtil=new SeleniumUtil();
    protected String pageSource="";
    protected String postUrl;

    private String driverPath="D:\\BaiduNetdiskDownload\\chromedriver.exe";

    public BaseSite(String login_url_, String waitingForUrl,String userTag_, String passTag_, String btnTag_, String userName_, String password_) throws IOException {
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
        seleniumUtil.warpTimeOut(driver, 10,waitingForUrl);
        upDateCookie();
        pageSource=driver.getPageSource();
    }

    public void postMessage(String url,String contentTag,String content,String buttonTag){
        System.out.println("开始发布内容");
        //httpHelper.sentGet("http://service.mtime.com/Service/Twitter.msi?Ajax_CallBack=true&Ajax_CallBackType=Mtime.Service.Pages.TwitterService&Ajax_CallBackMethod=PostTweetCrossDomainByFlash&Ajax_CrossDomain=1&Ajax_RequestUrl=http%3A%2F%2Fmovie.mtime.com%2F242167%2F&t=201911920535539339&Ajax_CallBackArgument0=%E7%9A%84%E7%A1%AE%E6%98%AF%E5%BC%95%E5%85%A5%E6%B7%B1%E6%80%9D%E7%9A%84%E7%94%B5%E5%BD%B1&Ajax_CallBackArgument1=&Ajax_CallBackArgument2=0&Ajax_CallBackArgument3=1&Ajax_CallBackArgument4=242167&Ajax_CallBackArgument5=-1&Ajax_CallBackArgument6=0&Ajax_CallBackArgument7=0" );
        driver.get(url);
        WebElement tex    = driver.findElement(By.id(contentTag));
        tex.sendKeys(content);
        WebElement button2   = driver.findElement(By.id(buttonTag));
        button2.click();
    }
    public void postMessage(String url,String title,String titleTag,String content,String contentTag,String selectTag,int selectIndex,String buttonTag){
        System.out.println("开始发布内容:"+url);
        //httpHelper.sentGet("http://service.mtime.com/Service/Twitter.msi?Ajax_CallBack=true&Ajax_CallBackType=Mtime.Service.Pages.TwitterService&Ajax_CallBackMethod=PostTweetCrossDomainByFlash&Ajax_CrossDomain=1&Ajax_RequestUrl=http%3A%2F%2Fmovie.mtime.com%2F242167%2F&t=201911920535539339&Ajax_CallBackArgument0=%E7%9A%84%E7%A1%AE%E6%98%AF%E5%BC%95%E5%85%A5%E6%B7%B1%E6%80%9D%E7%9A%84%E7%94%B5%E5%BD%B1&Ajax_CallBackArgument1=&Ajax_CallBackArgument2=0&Ajax_CallBackArgument3=1&Ajax_CallBackArgument4=242167&Ajax_CallBackArgument5=-1&Ajax_CallBackArgument6=0&Ajax_CallBackArgument7=0" );
        driver.get(url);
        WebElement tex    = driver.findElement(By.id(contentTag));
        tex.sendKeys(content);
        WebElement tex2    = driver.findElement(By.id(titleTag));
        tex2.sendKeys(title);
        Select select=new Select(driver.findElement(By.id(selectTag)));
        select.selectByIndex(selectIndex);
        WebElement button2   = driver.findElement(By.id(buttonTag));
        button2.click();
    }
    public void upDateCookie(){
        Set<Cookie> ss= driver.manage().getCookies();
        System.out.println("当前cookies:");
        for(Cookie item:ss){
            System.out.println(item.getName()+":"+item.getValue());
        }
        ss= driver.manage().getCookies();
        BasicCookieStore basicCookieStore=seleniumUtil.CookieSet(ss);
        httpHelper.setCookie(basicCookieStore);
    }
    public String getPostUrl(){
        return postUrl;
    }
}
