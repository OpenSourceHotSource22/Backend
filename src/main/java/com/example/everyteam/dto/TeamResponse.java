package com.example.everyteam.dto;

import com.example.everyteam.domain.Team;
import com.example.everyteam.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class TeamResponse {

    @NoArgsConstructor
    @Data
    public static class getUserTeamList {
        private UserResponse.getUser user;
        private List<Team> team;

        public getUserTeamList(UserResponse.getUser user, List<Team> team) {
            this.team = team;
            this.user = user;
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class getTeamPostList {
        private Team team;
        private Object userList;
        private Object postList;

    }
}
