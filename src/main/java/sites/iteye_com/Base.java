package sites.iteye_com;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sites.BaseSite;

import java.io.IOException;

public class Base extends BaseSite {
    public Base(String login_url_, String waitingForUrl, String userTag_, String passTag_, String btnTag_, String userName_, String password_) throws IOException {
        super(login_url_, waitingForUrl, userTag_, passTag_, btnTag_, userName_, password_);
    }
    public void test(){

    }
    public void findPostUrl(){
        Document doc= Jsoup.parse(this.pageSource);
        //System.out.println(this.pageSource);
        Elements newsHeadlines = doc.select(".login-fr a");
        for (Element headline : newsHeadlines) {
            System.out.println(headline.text()+":"+headline.absUrl("href"));
            this.postUrl=headline.absUrl("href")+"/admin/blogs/new";
            break;
        }
    }
}
