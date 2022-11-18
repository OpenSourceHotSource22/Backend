package com.example.everyteam.dto.user;

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

    @NoArgsConstructor
    @Data
    public static class getUser {
        private String id;

        public getUser(String id) {
            this.id = id;
        }
    }
}
