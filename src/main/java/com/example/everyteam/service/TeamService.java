package com.example.everyteam.service;

import com.example.everyteam.config.exception.BadRequestException;
import com.example.everyteam.domain.Belong;
import com.example.everyteam.domain.Team;
import com.example.everyteam.domain.User;
import com.example.everyteam.dto.TeamRequest;
import com.example.everyteam.dto.TeamResponse;
import com.example.everyteam.dto.UserResponse;
import com.example.everyteam.repository.BelongRepository;
import com.example.everyteam.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final BelongRepository belongRepository;


    public String createTeam(User user, TeamRequest.createTeam req) {
        Team team = Team.builder().code(randomCode()).name(req.getName()).build();
        teamRepository.save(team);
        Belong belong = Belong.builder().user(user).team(team).build();
        belongRepository.save(belong);
        return team.getCode();
    }

    public String joinTeam(User user, String teamCode) {
        Team team = teamRepository.findByCode(teamCode).orElseThrow(()->new BadRequestException("code를 다시 입력하세요"));
        if(belongRepository.countTeamCodeByUser(user.getUserIdx(), team.getTeamIdx())>0)
            throw new BadRequestException("이미 team에 등록된 유저입니다.");
        Belong belong = Belong.builder().user(user).team(team).build();
        belongRepository.save(belong);
        return team.getCode();
    }

    public TeamResponse.getUserTeamList getUserTeamList(User user) {
        List<Belong> belongs= belongRepository.findAllByUserIdx(user.getUserIdx());
        List<Team> teamList = new ArrayList<>();
        for(Belong b : belongs){
            teamList.add(b.getTeam());
        }
        UserResponse.getUser getUser = new UserResponse.getUser(user.getId());
        return new TeamResponse.getUserTeamList(getUser, teamList);

    }

    private String randomCode(){
        return RandomStringUtils.random(6,33,125,true,false);
    }


    public Team getTeam(String teamCode) {
        Team team = teamRepository.findByCode(teamCode).orElseThrow(()->new BadRequestException("잘못된 Team Code입니다."));
        return team;
    }

//
////    team에 속한 userList
//    public List<String> getUserList(String teamCode) {
//        List<String> userList = teamRepository.findAllByCode(teamCode);
//        return userList
//    }
}
