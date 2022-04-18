package com.cdf.mall.demo.defaultMethodTest;

/**
 * @Description TODO
 * @Author hanyaguang
 * @Date 2022/4/2 10:00
 * @Version 1.0
 */
public class Order extends AbstractUser implements IUser,IOrder,IClient {

    @Override
    public void getOrder() {
        System.out.println("进入Order类中的getOrder方法");
    }

    @Override
    public void getClient() {

    }

    public void get(){
        System.out.println("进入Order类中的get（)方法");
    }
   public void getMyClient(){
       System.out.println("进入Order中的getMyClient（）方法");
   };
}
