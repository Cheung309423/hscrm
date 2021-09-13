package com.hscrm.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Peter Cheung
 * @user PerCheung
 * @date 2021/9/6 23:34
 */
public class JsonUtil<T> {
    public String toString(int code, String message, T data) {
        ResponseEntity<T> responseEntity = new ResponseEntity<>();
        responseEntity.setCode(code);
        responseEntity.setMessage(message);
        responseEntity.setData(data);

        ObjectMapper mapper = new ObjectMapper();
        String result = "";
        try {
            result = mapper.writeValueAsString(responseEntity);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
