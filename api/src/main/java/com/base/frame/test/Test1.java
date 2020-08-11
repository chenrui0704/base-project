package com.base.frame.test;

import com.base.frame.util.AESUtil;
import org.springframework.util.DigestUtils;

import java.util.Base64;
import java.util.Date;

public class Test1 {

    public static void main(String[] args) {

        System.out.println(new String(Base64.getEncoder().encode("admin".getBytes())));
        System.out.println(new String(Base64.getEncoder().encode("123".getBytes())));
        System.out.println(new String(Base64.getDecoder().decode("YWRtaW4=".getBytes())));
        System.out.println(DigestUtils.md5DigestAsHex("123".getBytes()));

        System.out.println(AESUtil.decrypt("BBF5747A49A2B963595B84985DCE137D3F3F7E52AB66D935BE6EA1D6FF6385C2", "3b3w6dw10efs5h18", 0));


    }

}
