package com.example.everyteam.service;

import com.example.everyteam.config.exception.ErrorResponseStatus;
import com.example.everyteam.config.exception.TestException;
import org.springframework.stereotype.Service;

import static com.example.everyteam.config.exception.ErrorResponseStatus.*;

@Service
public class TestService {
    public String testException(int code){
        if(code == 400) throw new TestException(DATABASE_ERROR);
        return "code가 400이 아님";
    }
}
