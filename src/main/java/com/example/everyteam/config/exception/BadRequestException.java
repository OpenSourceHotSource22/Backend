package com.example.everyteam.config.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String s){
        super(s);
    }
}
