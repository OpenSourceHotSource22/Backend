package com.example.everyteam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class TeamRequest {
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class createTeam {
        private String name;
        private String description;
    }
}
