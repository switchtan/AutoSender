package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;


public class SeleniumDemo {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\BaiduNetdiskDownload\\chromedriver.exe");// chromedriver服务地址
        WebDriver driver    = new ChromeDriver();
        driver.get("https://passport.mtime.com/member/signin");

        Set<Cookie> ss= driver.manage().getCookies();
        for(Cookie item:ss){
            System.out.println(item.getName()+":"+item.getValue());
        }

        //https://www.cnblogs.com/test-my/p/5942387.html
        //这篇文章不错 https://blog.csdn.net/qq_22003641/article/details/79137327
        WebElement email    = driver.findElement(By.id("loginEmailText"));
        email.sendKeys("13326511991");
        WebElement passw    = driver.findElement(By.id("loginPasswordText"));
        passw.sendKeys("guavaguava00");
        WebElement button   = driver.findElement(By.id("loginButton"));
        button.click();

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver d) {
                String pageurl=driver.getCurrentUrl();
                if(pageurl.equals("http://www.mtime.com/")){
                    System.out.println("logined");
                    return true;
                }else {
                    System.out.println(pageurl+":no login");
                    return false;
                }
            } });


         ss= driver.manage().getCookies();
        for(Cookie item:ss){
            System.out.println(item.getName()+":"+item.getValue());
        }

    }
}
