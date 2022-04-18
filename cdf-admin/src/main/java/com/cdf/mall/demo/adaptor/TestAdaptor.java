package com.cdf.mall.demo.adaptor;

/**
 * @Description TODO
 * @Author hanyaguang
 * @Date 2022/4/2 16:11
 * @Version 1.0
 */
public class TestAdaptor {

    public static void main(String[] args) {
      RiskManagement riskManagement = new RiskManagement();
      ASensitiveWordsFilterAdaptor adaptor = new ASensitiveWordsFilterAdaptor(new ASensitiveWordsFilter());
      riskManagement.addSensitiveWordsFilter(adaptor);
      BSensitiveWordsFilterAdaptor bdaptor = new BSensitiveWordsFilterAdaptor(new BSensitiveWordsFilter());
        riskManagement.addSensitiveWordsFilter(bdaptor);
        riskManagement.filterSensitiveWords("敏感词1");
    }
}
