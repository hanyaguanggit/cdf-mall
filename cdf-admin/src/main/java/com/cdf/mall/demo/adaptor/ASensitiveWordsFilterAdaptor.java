package com.cdf.mall.demo.adaptor;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @Description 具体的适配器
 * @Author hanyaguang
 * @Date 2022/4/2 15:58
 * @Version 1.0
 */
public class ASensitiveWordsFilterAdaptor implements ISensitiveWordsFilter{
    private ASensitiveWordsFilter aFilter;

    public ASensitiveWordsFilterAdaptor(ASensitiveWordsFilter aFilter) {
        this.aFilter = aFilter;
    }

    @Override
    public String filter(String text) {
        return aFilter.filterSexyWords(text);
    }
}
