package test;

import HttpUtil.HttpHelper;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sites.BaseLogin;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 模仿登录iTeye
 * @author Li
 */
public class BaseLoginTest {

    public static void main(String[] args) throws Exception {


                JFrame jf = new JFrame("用户登录");
                jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                // 第 1 个 JPanel, 使用默认的浮动布局
                JPanel panel01 = new JPanel();


                // 创建一个垂直盒子容器, 把上面 3 个 JPanel 串起来作为内容面板添加到窗口
                Box vBox = Box.createVerticalBox();
                vBox.add(panel01);
        JOptionPane.showMessageDialog(panel01, "提示消息", "标题",JOptionPane.WARNING_MESSAGE);

        jf.setContentPane(vBox);

                jf.pack();
                jf.setLocationRelativeTo(null);
                jf.setVisible(true);



        BaseLogin baseLogin=new BaseLogin("https://passport.mtime.com/member/signin","loginEmailText"
                ,"loginPasswordText","loginButton"
        ,"13326511991","guavaguava00");
        baseLogin.postMessage("http://movie.mtime.com/246986/","","真的引人深思的电影啊");


    }


}

