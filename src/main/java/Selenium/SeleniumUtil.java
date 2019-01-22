package Selenium;

import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class SeleniumUtil {
    private boolean waittingFor(WebDriver driver,String url){
        boolean returnValue=false;
        String pageurl=driver.getCurrentUrl();
        if(pageurl.equals(url)){
            System.out.println("logined");
            returnValue=true;
            return returnValue;
        }else {
            System.out.println(pageurl+":no login");
            return returnValue;
        }
    }
    public void warpTimeOut(WebDriver driver,int timeout,String url){
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver d) {
                boolean returnValue=false;
                return waittingFor(driver,url);
            } });
    }
    //org.openqa.selenium.Cookie
    public BasicCookieStore CookieSet(Set<Cookie> ss){
        BasicCookieStore basicCookieStore=new BasicCookieStore();
        for(Cookie item:ss){
            System.out.println(item.getName()+":"+item.getValue());
            BasicClientCookie bcookie = new BasicClientCookie(item.getName(), item.getValue());
            bcookie.setDomain(item.getDomain());
            //System.out.println(item.getDomain()+":domain");
            bcookie.setExpiryDate(item.getExpiry());
            bcookie.setPath(item.getPath());
            basicCookieStore.addCookie(bcookie);
        }
        return basicCookieStore;
    }
}
