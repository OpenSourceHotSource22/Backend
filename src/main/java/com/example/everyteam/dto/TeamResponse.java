package com.example.everyteam.dto;

import com.example.everyteam.domain.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class TeamResponse {
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class getTeamPostList {
        private Team team;
        private Object userList;
        private Object postList;
    }
}
