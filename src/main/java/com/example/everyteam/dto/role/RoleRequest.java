package com.example.everyteam.dto.role;

import lombok.Data;

import java.util.List;
import java.util.Map;

public class RoleRequest {
    @Data
    public static class createRole {
        private String teamCode;
        private String title;
        private List<Map<String, Object>> role;
    }

    @Data
    public static class getUserList {
        private String teamCode;
    }

    @Data
    public static class createRoulette {
        private String teamCode;
        private String title;
        private String userId;
    }

}
