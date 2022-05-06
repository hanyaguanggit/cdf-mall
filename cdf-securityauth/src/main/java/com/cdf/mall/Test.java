package com.cdf.mall;

import com.cdf.mall.domain.SpringContextUtil;
import org.bouncycastle.jcajce.provider.asymmetric.rsa.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Description 测试
 * @Author hanyaguang
 * @Date 2022/5/6 9:15
 * @Version 1.0
 */
public class Test {
    @Autowired
    private SpringContextUtil springContextUtil;



       /*ApplicationContext context = springContextUtil.getApplicationContext();
       //Object o = context.getBean("memberDetail");
       // System.out.println(o);

        Object o1 = SpringContextUtil.getBean("memberDetail");
        System.out.println(o1);*/
       public static SecretKey generalKey(String stringKey) {
           stringKey = stringKey.trim();
           SecretKey key = new SecretKeySpec(stringKey.getBytes(), "HMACSHA256");
           return key;
       }

    public static void main(String[] args) {
        SecretKey key = generalKey("123123");
        System.out.println(key);
    }

}
