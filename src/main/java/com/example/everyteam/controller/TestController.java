package com.example.everyteam.controller;

import com.example.everyteam.config.exception.TestException;
import com.example.everyteam.dto.JsonResponse;
import com.example.everyteam.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @PostMapping("/test/date")
    public ResponseEntity<JsonResponse> testDateRequest( @RequestParam("request") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate requestdatetime){
        System.out.println(requestdatetime);
        return null;
    }


}
