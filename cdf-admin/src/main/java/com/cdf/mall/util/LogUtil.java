package com.cdf.mall.util;

import io.netty.util.internal.StringUtil;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.AbstractQueue;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

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

    /**
     * {@link Queue}
     * java 队列相关
     */
    public static void queue(){
        //阻塞队列
        Queue queue2 = new ArrayBlockingQueue(3);

        //把 e 加到 BlockingQueue 里，如果 BlockQueue 没有空间，则调用此方法的线程被阻断直到 BlockingQueue 里面有空间再继续.
        Queue queue = new ArrayDeque();
        queue.add("aa");
        queue.add("bb");
        queue.add("cc");

        System.out.println("原队列-->"+queue);

        //检索但不删除队列的头部，于peek（）不同之处在于为空时抛出异常。
        Object a = queue.element();
        //更安全的插入一个元素，于add不同在于当超出队列容量时插入会失败。
        queue.offer("dd");
        System.out.println("安全的插入了dd元素-->"+queue);
        //检索但不删除队列的头，如果返回是null说明队列为空。
        Object o = queue.peek();
        System.out.println("检索但不删除队列的头，如果返回是null说明队列为空"+queue);

        //检索并移除该队列的头部
        queue.poll();
        System.out.println("检索并移除该队列的头部后---->"+queue);
        //检索并移除该队列的头部，如何这个队列为空，会抛出异常。
        //queue.remove();

        queue.poll();
        System.out.println("再次检索并移除该队列的头部后---->"+queue);
        queue.clear();
        System.out.println("清除队列-->"+queue);
    }

    public static void main(String[] args) throws InterruptedException {
       // leftFillZero(123,"10");
       // complete0Number3(123,10);
       // rightFillZero("qwe",6);
        queue();
    }
}
