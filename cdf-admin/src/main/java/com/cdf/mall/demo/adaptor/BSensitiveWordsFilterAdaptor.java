package com.cdf.mall.demo.adaptor;

/**
 * @Description TODO
 * @Author hanyaguang
 * @Date 2022/4/2 16:08
 * @Version 1.0
 */
public class BSensitiveWordsFilterAdaptor implements ISensitiveWordsFilter{
    private BSensitiveWordsFilter bFilter;

    public BSensitiveWordsFilterAdaptor(BSensitiveWordsFilter bFilter) {
        this.bFilter = bFilter;
    }

    @Override
    public String filter(String text) {
        return bFilter.bFilterSexyWords(text);
    }
}
