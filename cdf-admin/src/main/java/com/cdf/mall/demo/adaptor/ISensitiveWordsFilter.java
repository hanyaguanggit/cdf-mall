package com.cdf.mall.demo.adaptor;

/**
 * @Description 适配器实现敏感词
 * @Author hanyaguang
 * @Date 2022/4/2 15:57
 * @Version 1.0
 */
public interface ISensitiveWordsFilter {
    String filter(String text);
}
