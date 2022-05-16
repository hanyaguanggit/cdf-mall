package com.cdf.mall.util;

import java.util.UUID;

/**
 * @Description 改造之后的Guid
 * @Author hanyaguang
 * @Date 2022/5/16 14:42
 * @Version 1.0
 */
public class GUID {
    public GUID() {
    }

    public static String get() {
        UUID uuid = UUID.randomUUID();
        System.out.println("uuid >>"+uuid);
        StringBuffer sb = new StringBuffer();
        get(sb, uuid.getMostSignificantBits());
        get(sb, uuid.getLeastSignificantBits());
        return sb.toString();
    }

    private static StringBuffer get(StringBuffer sb, long bits) {
        for(int var3 = 13; var3-- > 0; bits >>>= 5) {
            long low = bits & 31L;
            if (low < 10L) {
                sb.append((char)((int)(48L + low)));
            } else {
                sb.append((char)((int)(65L + (low - 10L))));
            }
        }
        return sb;
    }

    public static void main(String[] args) {
        String pass = GUID.get();
        System.out.println(pass);

    }
}
