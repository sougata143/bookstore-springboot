package com.sougata.bookstore;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {
    String exceptionMessage;
    HttpStatus status;

    public ApiException(String exceptionMessage, HttpStatus status) {
        this.exceptionMessage = exceptionMessage;
        this.status = status;
    }

    public ApiException(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public ApiException(Throwable throwable, HttpStatus status) {

        this.status = status;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
