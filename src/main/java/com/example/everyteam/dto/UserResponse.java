package com.example.everyteam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserResponse {
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class login {
        private String id;
        private String token;
    }
}
