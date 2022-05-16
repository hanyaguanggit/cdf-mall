package com.cdf.mall.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;

/**
 * @Description TODO
 * @Author hanyaguang
 * @Date 2022/5/16 14:43
 * @Version 1.0
 */
public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public JsonUtil() {
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public static String toJson(Object object) {
        return toJson(object, false);
    }

    public static String toJson(Object object, boolean pretty) {
        try {
            return pretty ? objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object) : objectMapper.writeValueAsString(object);
        } catch (Exception var3) {
            throw new RuntimeException(var3);
        }
    }

    public static <T> T toBean(String json, Class<T> clazz) {
        if (StringUtils.isEmpty(json)) {
            return null;
        } else {
            try {
                return objectMapper.readValue(json, clazz);
            } catch (Exception var3) {
                throw new RuntimeException(var3);
            }
        }
    }

    public static <T> List<T> toList(String json, Class<T> clazz) {
        if (StringUtils.isEmpty(json)) {
            return null;
        } else {
            CollectionType typeReference = TypeFactory.defaultInstance().constructCollectionType(List.class, clazz);

            try {
                return (List)objectMapper.readValue(json, typeReference);
            } catch (IOException var4) {
                throw new RuntimeException(var4);
            }
        }
    }

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
}
