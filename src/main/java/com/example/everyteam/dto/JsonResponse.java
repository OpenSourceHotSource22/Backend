package com.example.everyteam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResponse {
    //    private final boolean success;

    private boolean isSuccess;
    private int status;
    private String message;
    private Object result;
}
