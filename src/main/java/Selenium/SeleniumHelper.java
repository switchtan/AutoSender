package Selenium;

import HttpUtil.HttpHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

public class SeleniumHelper {
    protected WebDriver driver;


    private String driverPath="D:\\BaiduNetdiskDownload\\chromedriver.exe";

    public SeleniumHelper() throws IOException {
        System.setProperty("webdriver.chrome.driver", driverPath);// chromedriver服务地址
        this.driver = new ChromeDriver();

    }

    public String getHtml(String url) throws InterruptedException {
        Thread.sleep(2500);
        try {
            driver.get(url);
            return driver.getPageSource();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return "";
        }


    }
}
