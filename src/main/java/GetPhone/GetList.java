package GetPhone;

import HttpUtil.HttpHelper;
import Selenium.SeleniumHelper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GetList {
    HttpHelper httpHelper =new HttpHelper();
    File file =new File("guava.csv");
    FileWriter fileWritter;
    SeleniumHelper seleniumHelper;
    public GetList() throws IOException {
        if(!file.exists()){
            file.createNewFile();
        }
        //
        // seleniumHelper=new SeleniumHelper();
        //true = append file

    }
    public void  finalize() throws IOException {
        //fileWritter.close();
    }
    public void getPhone(String url2) throws IOException, InterruptedException {

        String html=httpHelper.sentGet(url2);
        //System.out.println(html);
        Document doc = Jsoup.parse(html);
        Elements links = doc.select("dl.codl dd");
        int i=0;
        for(Element link : links){
            //System.out.println("company" + i + ":" + link.text());
        }
        if(links.size()>=5){
            String phone= links.get(3).text();
            if(phone.length()==11) {
                System.out.print(links.get(2).text() + ",");
                System.out.println(links.get(3).text());
                fileWritter = new FileWriter("guava.csv",true);
                fileWritter.write(links.get(2).text() + ","
                        +links.get(3).text()+"\n");
                fileWritter.close();
            }
        }
    }

    public void getCompanyUrl(String url2) throws IOException, InterruptedException {

        String html=httpHelper.sentGet(url2);
        if(html.indexOf("您访问的太快了，请联系我们客服")>0) {
            System.out.println(html);
        }
        Document doc = Jsoup.parse(html);
        Elements links = doc.select("ul.companylist a");
        int i=0;
        for(Element link : links){
            i=i+1;
            String url =link.attr("href");
            if(url.indexOf("co")>0   || url.indexOf("search")>0) {
                if(url.indexOf("http")==-1){
                    url="http:"+url;
                }
                System.out.println("company" + i + ":" + url);
                getPhone(url);
            }
        }
    }
    public void getList() throws IOException, InterruptedException {
        //String html=httpHelper.sentGet("https://www.11467.com/tianjin/");
        String html=httpHelper.sentGet("https://www.11467.com/qingdao/");
        Document doc = Jsoup.parse(html);
        Elements links = doc.select(".boxcontent a");
        int i=0;
        for(Element link : links){
            i=i+1;
            String url =link.attr("href");
            if(url.indexOf("dir")>0   || url.indexOf("search")>0) {
                if(url.indexOf("http")==-1){
                    url="http:"+url;
                }
                System.out.println("g" + i + ":" + url);
                getCompanyUrl(url);
            }
        }
    }
}
