package GetPhone;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class GetListTest {

    @Test
    public void getList() throws IOException, InterruptedException {
        GetList getList=new GetList();
        getList.getList();
    }

    @Test
    public void getCompanyUrl() throws IOException, InterruptedException {
        GetList getList= new GetList();
        getList.getCompanyUrl("http://www.11467.com/tianjin/dir/a.htm");
    }

    @Test
    public void getPhone() throws IOException, InterruptedException {
        GetList getList= new GetList();
        //getList.getPhone("http://www.11467.com/qiye/67739823.htm");
        //getList.getPhone("http://www.11467.com/tianjin/co/115762.htm");
        getList.getPhone("http://www.11467.com/qiye/61472627.htm");
    }
}