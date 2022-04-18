package com.cdf.mall.demo.adaptor;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

/**
 * @Description A 敏感词过滤器(第三方提供的原始接口)
 * @Author hanyaguang
 * @Date 2022/4/2 15:59
 * @Version 1.0
 */
public class ASensitiveWordsFilter {
    // A敏感词过滤系统提供的接口
    // text是原始文本，函数输出用***替换敏感词之后的文本
    public String filterSexyWords(String text) {
        System.out.println("进入A适配器的过滤方法。text="+text);
        return text;
    };

}
