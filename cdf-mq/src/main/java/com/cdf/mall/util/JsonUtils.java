package com.cdf.mall.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * @Description json工具类
 * @Author hanyaguang
 * @Date 2022/5/30 16:46
 * @Version 1.0
 */
public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public JsonUtils() {
    }

    public static String toJson(Object object) {
        return toJson(object, false);
    }

    public static String toJson(Object object, boolean pretty) {
        try {
            return pretty ? objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object) : objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static <T> T toBean(String json, Class<T> clazz) {
        if (StringUtils.isEmpty(json)) {
            return null;
        } else {
            try {
                return objectMapper.readValue(json, clazz);
            } catch (IOException var3) {
                throw new RuntimeException(var3);
            }
        }
    }

    public static JsonNode parse(String json) {
        try {
            return objectMapper.readTree(json);
        } catch (IOException var2) {
            var2.printStackTrace();
            return null;
        }
    }
}
