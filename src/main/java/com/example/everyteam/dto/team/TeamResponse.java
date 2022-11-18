package com.example.everyteam.dto.team;

import com.example.everyteam.domain.Team;
import com.example.everyteam.dto.user.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class TeamResponse {

    @NoArgsConstructor
    @Data
    public static class getUserTeamList {
        private UserResponse.getUser user;
        private List<TeamResponse.teamList> team;

        public getUserTeamList(UserResponse.getUser user, List<TeamResponse.teamList> team) {
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

    @NoArgsConstructor
    @Data
    public static class teamList {
        private Team team;
        private int countUser;

        public teamList(Team team, int countUser) {
            this.team = team;
            this.countUser = countUser;
        }
    }
}
