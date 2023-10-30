package com.mmhtoo.demo.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Data
@Builder
@ToString
public class Response {
    private HttpStatus status;
    private String description;
}
