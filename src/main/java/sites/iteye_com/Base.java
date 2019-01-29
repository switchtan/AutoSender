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

}
