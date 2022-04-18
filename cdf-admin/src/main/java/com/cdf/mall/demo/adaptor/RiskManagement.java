package com.cdf.mall.demo.adaptor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 适配器管理器
 * @Author hanyaguang
 * @Date 2022/4/2 16:04
 * @Version 1.0
 */
public class RiskManagement {
    private List<ISensitiveWordsFilter> filters = new ArrayList<>();

    public void addSensitiveWordsFilter(ISensitiveWordsFilter filter) {
        filters.add(filter);
    }

    public String filterSensitiveWords(String text) {
        String maskedText = text;
        for (ISensitiveWordsFilter filter : filters) {
            maskedText = filter.filter(maskedText);
        }
        return maskedText;
    }
}
