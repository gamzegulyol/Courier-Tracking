package com.example.Courier.Tracking.TestUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String convertObjectToJson(Object object) throws Exception {
        return objectMapper.writeValueAsString(object);
    }
}
