package com.cdf.mall.util;

import io.netty.util.internal.StringUtil;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @Description 日志工具类
 * @Author hanyaguang
 * @Date 2022/4/19 15:42
 * @Version 1.0
 */
public class LogUtil<T> {

    private static final char[] hexDigit = {
            '0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'
    };

    /**
     * 对象比较器  比较两个对象中的任一字段是否相同。
     * 比较结果eg：1、字段名称loginName,旧值:liu,新值:gu;2、字段名称address,旧值:hunan,新值:neimenggu
     * @param oldBean
     * @param newBean
     * @return
     */
    public String compareObject(Object oldBean, Object newBean) {
        String str = "";

        T pojo1 = (T) oldBean;
        T pojo2 = (T) newBean;
        try {
            Class clazz = pojo1.getClass();
            Field[] fields = pojo1.getClass().getDeclaredFields();
            int i = 1;
            for (Field field : fields) {
                if ("serialVersionUID".equals(field.getName())) {
                    continue;
                }
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                Method getMethod = pd.getReadMethod();
                Object o1 = getMethod.invoke(pojo1);
                Object o2 = getMethod.invoke(pojo2);
                if (o1 == null || o2 == null) {
                    continue;
                }
                if (!o1.toString().equals(o2.toString())) {
                    if (i != 1) {
                        str += ";";
                    }
                    str += i + "、字段名称" + field.getName() + ",旧值:" + o1 + ",新值:" + o2;
                    i++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }


    /**
     * 生成指定位数的数字，例如：输入一个123，要获取5位数。结果为：00123；
     * @param oldStr 原始字符串
     * @param digits 位数
     * @return 指定位数的字符串
     */
    public static void leftFillZero(int oldStr,String digits){
         //数字前补0
         System.out.println("数字位数不足补0-->>"+String.format("%0"+digits+"d", oldStr));

         //字符串长度不足补空格
        String s = String.format("%"+digits+"s", "AA");
        System.out.println("字符串位数不足补空格--->>"+s);

        //字符串前补0
        String b = String.format("%"+digits+"s", "AA").replace(" ","0");
        System.out.println("字符串长度不够补0--->>"+b);
    }

    /**
     * 字符串根据指定位数，若位数不够进行右边补0
     * @param oldStr
     * @param length
     */
    public static void rightFillZero(String oldStr,int length){
        String newFormat = "0000000000000000000000000000000000000000000".substring(0,length);
        StringBuilder stringBuilder = new StringBuilder(oldStr);
        //不够四位进行补0操作
        if(oldStr.length()<length){
            stringBuilder.append(newFormat.substring(oldStr.length(),length));
        }
        String newStr = stringBuilder.toString();
        System.out.println("字符串右边位数不够补0-->>"+newStr);
    }

    public static void main(String[] args) {
        leftFillZero(123,"10");
       // complete0Number3(123,10);
        rightFillZero("qwe",6);
    }
}
