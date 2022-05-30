package com.cdf.mall.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description 测试@Data注解，反编译后查看jar包中此类的equals方法和hashCode方法。
 * @Author hanyaguang
 * @Date 2022/5/30 11:31
 * @Version 1.0
 */
@Data
public class GoodsDetailResult {
    private String goodsId;
    private String intro;
    private boolean needCheck;
    private String checkDescribe;
    private String productNameEn;
    private BigDecimal marketPrice;
    private BigDecimal salesPrice;
    private BigDecimal estimatePrice;
    private Integer isPackage;
    private Integer isVipOnly;
    private Integer isPreSale;
    private Integer isFlaw;
    private String skuType;
    private Integer active;
    private Integer access;
    private Integer showMarkerPrice;
}
