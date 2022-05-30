package com.cdf.mall.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 金额工具类
 * @author daimingdong
 */
public class AmountUtils {

    private static final String ZERO_AMOUNT = "0.00";

    /**
     * 对两个字符串做减法（不丢失精度）
     *
     * @param src  被减数
     * @param dest 减数
     * @return String
     */
    public static String subtract(String src, String dest) {
        BigDecimal srcDeci = null2Zero(src);
        BigDecimal desctDeci = null2Zero(dest);
        return String.valueOf(srcDeci.subtract(desctDeci));
    }

    /**
     * 对两个字符串做加法（不丢失精度）
     *
     * @param src
     * @param dest
     * @return String
     */
    public static String add(String src, String dest) {
        BigDecimal srcDeci = null2Zero(src);
        BigDecimal desctDeci = null2Zero(dest);
        return String.valueOf(srcDeci.add(desctDeci));
    }

    /**
     * 多个金额字符串相加，至少2个参数
     */
    public static BigDecimal addOverTwo(String amount1, String amount2, String... amounts) {
        BigDecimal result = null2Zero(amount1).add(null2Zero(amount2));
        for (String amount : amounts) {
            result = result.add(null2Zero(amount));
        }
        return result;
    }

    /**
     *
     * @param amountStr
     * @return
     */
    public static BigDecimal null2Zero(String amountStr) {
        BigDecimal amount = BigDecimal.ZERO;
        if (StringUtils.isNotBlank(amountStr)) {
            amount = new BigDecimal(amountStr);
        }
        return amount;
    }

    /**
     * 加法
     * @param src
     * @param dest
     * @return
     */
    public static BigDecimal addBigDecimal(String src, String dest) {
        BigDecimal srcDeci = new BigDecimal(src);
        BigDecimal desctDeci = new BigDecimal(dest);
        return srcDeci.add(desctDeci);
    }

    /**
     * 描述：BIG转String 精度采用默认2位小数
     *
     * @param bd
     * @return
     */
    public static String bigDecimalToString(BigDecimal bd) {
        return bigDecimalToString(bd, 2);
    }

    /**
     * 方法说明 描述：BIG转String 可以控制精度四舍五入
     *
     * @param bd
     * @param w  精度
     * @return
     * @returnType String
     */
    public static String bigDecimalToString(BigDecimal bd, int w) {
        return ((new BigDecimal(ZERO_AMOUNT)).add(bd)).setScale(w, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 比较传入字符串与0
     *
     * @param src
     * @return 0 相等，1 大于0, -1 小于0
     */
    public static int compareToZero(String src) {
        return new BigDecimal(src).compareTo(BigDecimal.ZERO);
    }


    /**
     * 金额取反
     *
     * @param src
     * @return 负数返回正数，正数返回负数
     */
    public static String negate(String src) {
        return new BigDecimal(src).negate().toString();
    }

    /**
     * 字符串金额大小比较
     *
     * @param src
     * @param dest
     * @return src在数字上小于、等于或大于 dest 时，返回 -1、0 或 1
     */
    public static int compare(String src, String dest) {
        BigDecimal srcDeci = new BigDecimal(src);
        BigDecimal destDeci = new BigDecimal(dest);
        return srcDeci.compareTo(destDeci);
    }

    /**
     * 对两个字符串做乘法（不丢失精度）
     *
     * @param src
     * @param dest
     * @return
     */
    public static String multiply(String src, String dest) {
        BigDecimal srcDeci = new BigDecimal(src);
        BigDecimal destDeci = new BigDecimal(dest);

        return srcDeci.multiply(destDeci).toString();
    }

    /**
     * 分为单位的数字转换为元
     *
     * @param fenStr
     * @return
     */
    public static String fen2Yuan(String fenStr) {
        if (StringUtils.isBlank(fenStr)) {
            return fenStr;
        }
        return new BigDecimal(fenStr).divide(new BigDecimal("100.0")).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 以元为单位的数字转换为分
     *
     * @param yuanStr
     * @return
     */
    public static String yuan2Fen(String yuanStr) {
        if (StringUtils.isBlank(yuanStr)) {
            return yuanStr;
        }
        return new BigDecimal(yuanStr).multiply(new BigDecimal("100.0")).setScale(0, BigDecimal.ROUND_HALF_UP)
                .toString();
    }

    /**
     * 对两个字符串做乘法，根据精度进行四舍五入
     *
     * @param src
     * @param dest
     * @param scale
     * @return
     */
    public static String multiply(String src, String dest, int scale) {
        BigDecimal srcDeci = new BigDecimal(src);
        BigDecimal destDeci = new BigDecimal(dest);
        return srcDeci.multiply(destDeci).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 对两个字符串做除法（不丢失精度）
     *
     * @param src
     * @param dest
     */
    public static String divide(String src, String dest) {
        BigDecimal srcDeci = new BigDecimal(src);
        BigDecimal destDeci = new BigDecimal(dest);
        return String.valueOf(srcDeci.divide(destDeci));
    }

    /**
     * 对两个字符串做除法（不丢失精度），四舍五入保留指定位数的小数
     *
     * @param src
     * @param dest
     * @param scale 保留位数
     */
    public static String divide(String src, String dest, int scale) {
        BigDecimal srcDeci = new BigDecimal(src);
        BigDecimal destDeci = new BigDecimal(dest);
        return srcDeci.divide(destDeci, scale, RoundingMode.HALF_UP).toString();
    }

    /**
     * 空字符串，null转换成0
     *
     * @param src
     * @return
     */
    public static BigDecimal nullToZero(String src) {
        if (StringUtils.isBlank(src)) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(src);
    }

}
