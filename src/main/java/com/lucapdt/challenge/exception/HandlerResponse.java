package com.lucapdt.challenge.exception;

import lombok.Data;

import java.util.Date;

@Data
public class HandlerResponse {
    private Integer statusCode;
    private String message;
    private Date timestamp;
}
