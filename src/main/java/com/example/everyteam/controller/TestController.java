package com.example.everyteam.controller;

import com.example.everyteam.config.exception.TestException;
import com.example.everyteam.dto.JsonResponse;
import com.example.everyteam.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.everyteam.config.exception.ErrorResponseStatus.DATABASE_ERROR;


@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;
    @PostMapping("/test/{error}")
    public ResponseEntity<JsonResponse> testErroroResponse(@PathVariable int error){
        testService.testException(error);
        return null;
    }
}
