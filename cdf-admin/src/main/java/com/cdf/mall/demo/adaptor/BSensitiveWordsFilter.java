package com.cdf.mall.demo.adaptor;

/**
 * @Description TODO
 * @Author hanyaguang
 * @Date 2022/4/2 16:09
 * @Version 1.0
 */
public class BSensitiveWordsFilter {

    public String bFilterSexyWords(String text) {
        System.out.println("进入B适配器的过滤方法。text="+text);
        return text;
    };
}
