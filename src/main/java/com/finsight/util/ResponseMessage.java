package com.finsight.utils;

import org.springframework.http.HttpStatus;

public class ResponseMessage {
    private String status;
    private String message;
    private HttpStatus httpStatus;

    public ResponseMessage(String status, String message, HttpStatus httpStatus) {
        this.status = status;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    // Getters and Setters
}
