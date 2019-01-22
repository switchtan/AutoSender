package test;

import HttpUtil.HttpHelper;
import Selenium.SeleniumUtil;
import org.apache.http.impl.client.BasicCookieStore;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.Callable;
class MyThread implements Runnable{
    @Override public void run() {
        // TODO Auto-generated method stub
        System.out.println(Thread.currentThread().getName()+"-->我是通过实现接口的线程实现方式！");
        WebDriver driver    = new ChromeDriver();
        driver.get("http://www.163.com");
    }
}



public class TwoBrowser {
    private HttpHelper httpHelper=new HttpHelper();

    public static void main(String[] args) throws Exception{
        String driverPath="D:\\BaiduNetdiskDownload\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",driverPath);// chromedriver服务地址
        Thread t1 = new Thread(new MyThread());
        t1.start();
        WebDriver driver2    = new ChromeDriver();
        driver2.get("http://www.baidu.com");

    }

    public void test(){
        System.out.println("go");
    }
}
