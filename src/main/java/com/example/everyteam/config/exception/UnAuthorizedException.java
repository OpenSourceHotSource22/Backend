package com.example.everyteam.config.exception;

public class UnAuthorizedException extends RuntimeException{
    public UnAuthorizedException(String s){
        super(s);
    }
}
