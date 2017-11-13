package com.trp.mifid.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    private static final AppLogger logger = new AppLogger(JsonUtil.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    private JsonUtil() {
    }

    public static String toJson(Object obj) {
        if (obj==null) {
            return "null";
        }

        String json = "undefined";
        try {
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error(
                    "Failed to create JSON representation for object class {1}",
                    obj.getClass().getName());
        }

        return json;
    }
}
