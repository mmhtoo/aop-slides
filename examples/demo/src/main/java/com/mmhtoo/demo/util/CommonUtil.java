package com.mmhtoo.demo.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;

public class CommonUtil {

    public static Object requestBody(BufferedReader reader) throws IOException {
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }

        // Parse the request body as JSON
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(requestBody.toString(), Object.class);
    }

}
