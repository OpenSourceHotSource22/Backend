package com.example.everyteam.config.exception;

public class GlobalException extends RuntimeException{
    private ErrorResponseStatus status;

    public GlobalException(ErrorResponseStatus status) {
        this.status = status;
    }
    public ErrorResponseStatus getStatus() {
        return this.status;
    }
}
