package com.example.everyteam.config.exception;

public class NotFoundException extends RuntimeException{
    private ErrorResponseStatus status;

    public NotFoundException(ErrorResponseStatus status) {
        this.status = status;
    }
    public ErrorResponseStatus getStatus() {
        return this.status;
    }

}
