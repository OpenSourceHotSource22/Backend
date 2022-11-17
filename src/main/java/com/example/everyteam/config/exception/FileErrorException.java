package com.example.everyteam.config.exception;

public class FileErrorException extends RuntimeException{
    private ErrorResponseStatus status;

    public FileErrorException(ErrorResponseStatus status) {
        this.status = status;
    }
    public ErrorResponseStatus getStatus() {
        return this.status;
    }
}
