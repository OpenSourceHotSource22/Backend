package com.example.everyteam.service;

import com.example.everyteam.config.exception.BadRequestException;
import com.example.everyteam.domain.Belong;
import com.example.everyteam.domain.Team;
import com.example.everyteam.domain.User;
import com.example.everyteam.dto.TeamRequest;
import com.example.everyteam.repository.BelongRepository;
import com.example.everyteam.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

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

    public List<Belong> getUserTeamList(User user) {
        List<Belong> belongs= belongRepository.findAllByUserIdx(user.getUserIdx());
        return belongs;
    }

    public String randomCode(){
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 6; i++) {
            int index = rnd.nextInt(3);
            switch (index) {
                case 0:
                    key.append(((int) (rnd.nextInt(26)) + 97));
                    break;
                case 1:
                    key.append(((int) (rnd.nextInt(26)) + 65));
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    break;
            }
        }
        return key.toString();
    }


    public Team getTeam(String teamCode) {
        Team team = teamRepository.findByCode(teamCode).orElseThrow(()->new BadRequestException("잘못된 Team Code입니다."));
        return team;
    }

    //team에 속한 userList
//    public List<String> getUserList(String teamCode) {
//        List<String> userList = teamRepository.findUserByCode(teamCode);
//    }
}
