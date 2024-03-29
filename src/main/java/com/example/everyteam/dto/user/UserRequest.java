package com.example.everyteam.dto.user;

import com.example.everyteam.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserRequest {
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class join {
        private String id;
        private String pwd;
        private String email;
        private String name;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class login {
        private String id;
        private String pwd;
    }
}
