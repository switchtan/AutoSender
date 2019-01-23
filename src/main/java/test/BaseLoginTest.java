package test;

import sites.BaseSite;

import javax.swing.*;

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



        BaseSite baseLogin=new BaseSite("https://passport.mtime.com/member/signin","http://www.mtime.com/","loginEmailText"
                ,"loginPasswordText","loginButton"
        ,"13326511991","guavaguava00");
        baseLogin.postMessage("http://movie.mtime.com/246986/"
                ,"tweetBoxUp","真的引人深思的电影啊","tweetButtonUp");


    }


}

