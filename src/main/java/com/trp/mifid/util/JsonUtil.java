package com.trp.mifid.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    private final AppLogger logger;
    private final ObjectMapper mapper;

    public JsonUtil() {
        logger = new AppLogger(this.getClass());
        mapper = new ObjectMapper();
    }

    public String toJson(Object obj) {
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
