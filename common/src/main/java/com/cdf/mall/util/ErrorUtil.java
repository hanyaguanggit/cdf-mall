package com.cdf.mall.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Description TODO
 * @Author hanyaguang
 * @Date 2022/5/16 14:41
 * @Version 1.0
 */
public class ErrorUtil {
    public ErrorUtil() {
    }

    public static String stackTraceToString(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}
