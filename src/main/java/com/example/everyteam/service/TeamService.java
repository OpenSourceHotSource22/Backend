package com.example.everyteam.service;

import com.example.everyteam.config.exception.BadRequestException;
import com.example.everyteam.domain.Belong;
import com.example.everyteam.domain.Team;
import com.example.everyteam.domain.User;
import com.example.everyteam.dto.team.TeamRequest;
import com.example.everyteam.dto.team.TeamResponse;
import com.example.everyteam.dto.user.UserResponse;
import com.example.everyteam.repository.BelongRepository;
import com.example.everyteam.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.List;

import static com.example.everyteam.config.exception.ErrorResponseStatus.*;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final BelongRepository belongRepository;
    private final ImageService imageService;


    @Transactional
    public String createTeam(User user, TeamRequest.createTeam req, MultipartFile file) {

        String newCode = randomCode();

        String imgUrl = null;

        if(file.getSize()>0){
            imgUrl = imageService.uploadToStorage("team","everyTeam_"+newCode,file);
        }

        Team team = Team.builder().code(newCode).name(req.getName()).description(req.getDescription()).imgUrl(imgUrl).build();
        teamRepository.save(team);

        Belong belong = Belong.builder().user(user).team(team).build();
        belongRepository.save(belong);

        return team.getCode();
    }

    public String joinTeam(User user, String teamCode) {
        Team team = teamRepository.findByCode(teamCode).orElseThrow(()->new BadRequestException(TEAM_CODE_ERROR));
        if(belongRepository.countTeamCodeByUser(user.getUserIdx(), team.getTeamIdx())>0)
            throw new BadRequestException(EXIST_USER_IN_TEAM);
        Belong belong = Belong.builder().user(user).team(team).build();
        belongRepository.save(belong);
        return team.getCode();
    }

    public TeamResponse.getUserTeamList getUserTeamList(User user) {
        List<Belong> belongs= belongRepository.findAllByUserIdx(user.getUserIdx());
        List<TeamResponse.teamList> teamList = new ArrayList<>();
        for(Belong b : belongs){
            int countUser = belongRepository.countTeam(b.getTeam().getTeamIdx());
            teamList.add(new TeamResponse.teamList(b.getTeam(),countUser));
        }

        UserResponse.getUser getUser = new UserResponse.getUser(user.getId());

        return new TeamResponse.getUserTeamList(getUser, teamList);

    }

    public String randomCode(){
        return RandomStringUtils.random(6,33,125,true,false);
    }


    public Team getTeam(String teamCode) {
        Team team = teamRepository.findByCode(teamCode).orElseThrow(()->new BadRequestException(TEAM_CODE_ERROR));
        return team;
    }


//    team에 속한 userList
    public List<String> getUserList(String teamCode) {
        List<String> userList = belongRepository.findUserByTeam(teamCode);
        return userList;
    }

    public void UserOnTeam(String teamCode, String userId) {
        List<String> userList = belongRepository.findUserByTeam(teamCode);
        if(userList.contains(userId)){
            System.out.println("true");
        }else{
            System.out.println("false");
            throw new BadRequestException(NOT_FOUND_USER_IN_TEAM);
        }
    }

    public void updateTopImage(Team team, MultipartFile file) {
        String imgUrl=null;
        if(file.getSize()>0){
            imgUrl = imageService.uploadToStorage("team","everyTeam_topImg_"+team.getCode(),file);
            team.setTopImgUrl(imgUrl);
            teamRepository.save(team);
        }
        else throw new BadRequestException(FILE_SAVE_ERROR);
    }


    ///////TODO : test Team List
    public List<Team> getTeamList() {
        return teamRepository.findAll();
    }
}
