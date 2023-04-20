package com.makaia.express.exceptions;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ResponseException {
    private String message;
    private HttpStatus status;
    private ZonedDateTime time;

    public ResponseException(String message, HttpStatus status, ZonedDateTime time) {
        this.message = message;
        this.status = status;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public ZonedDateTime getTime() {
        return time;
    }
}
