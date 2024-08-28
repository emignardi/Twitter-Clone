package org.ac.cst8277.mignardi.eric.usermanagementservice;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpResponseExtractor {
    public static <T> T extractDataFromHttpClientResponse(Object data, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(data, clazz);
    }
}