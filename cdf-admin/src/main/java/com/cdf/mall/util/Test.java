package com.cdf.mall.util;


/**
 * @Description 测试
 * @Author hanyaguang
 * @Date 2022/5/11 9:42
 * @Version 1.0
 */
public class Test {

        public volatile int inc = 0;

        public void increase() {
            inc++;
        }

        public static void main(String[] args) {

            final Test test = new Test();

            for(int i=0;i<10;i++){

                new Thread(){

                    public void run() {

                        for(int j=0;j<1000;j++)

                            test.increase();

                    };

                }.start();

            }

            while(Thread.activeCount()>1)  //保证前面的线程都执行完

                Thread.yield();

            System.out.println(test.inc);

        }


}
