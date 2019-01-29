package test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeSleep {
    public void getTime() throws InterruptedException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        Thread.sleep(2000);
        System.out.println(df.format(new Date()));
    }
}
