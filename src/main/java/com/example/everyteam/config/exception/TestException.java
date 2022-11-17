package com.example.everyteam.config.exception;

public class TestException extends RuntimeException{
    private ErrorResponseStatus status;

    public TestException(ErrorResponseStatus status) {
        this.status = status;
    }

    public ErrorResponseStatus getStatus() {
        return this.status;
    }

}
