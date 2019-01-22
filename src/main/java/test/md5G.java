package test;

import org.apache.commons.codec.digest.DigestUtils;

public class md5G {
    public void byUtils(){
        String encodeStr= DigestUtils.md5Hex("guavaguava00");
        System.out.println("MD5加密后的字符串为:encodeStr="+encodeStr);
        //return encodeStr;
    }
}
