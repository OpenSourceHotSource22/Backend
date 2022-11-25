package com.example.everyteam.dto.user;

import com.example.everyteam.domain.User;
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
        private String color;

        public getUser(User user) {
            this.id = user.getId();
            this.color = user.getColor();
        }
    }
}
