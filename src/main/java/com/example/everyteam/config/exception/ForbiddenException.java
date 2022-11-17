package com.example.everyteam.config.exception;

public class ForbiddenException extends RuntimeException{
    private ErrorResponseStatus status;

    public ForbiddenException(ErrorResponseStatus status) {
        this.status = status;
    }
    public ErrorResponseStatus getStatus() {
        return this.status;
    }
}
