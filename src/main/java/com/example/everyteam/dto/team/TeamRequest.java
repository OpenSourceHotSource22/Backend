package com.example.everyteam.dto.team;

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

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class join {
        private String teamCode;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class updateTeam {
        private String teamCode;
    }
}
