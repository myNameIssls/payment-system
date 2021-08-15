package cn.tyrone.payment.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String writeValueAsString(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T readValue(String value, Class<T> valueType) {

        try {
            return objectMapper.readValue(value, valueType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


}
