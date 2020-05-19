package com.mcb.creditfactory;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcb.creditfactory.dto.Collateral;

public class JsonUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JsonUtil(){

    }

    private static ObjectMapper getMapper() {
        return MAPPER;
    }

    public static String getAsJson(Collateral collateral) throws JsonProcessingException {
        return getMapper().writeValueAsString(collateral);
    }
}
