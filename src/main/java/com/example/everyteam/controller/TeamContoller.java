package com.example.everyteam.controller;

import com.example.everyteam.config.JwtService;
import com.example.everyteam.domain.Belong;
import com.example.everyteam.domain.Post;
import com.example.everyteam.domain.Team;
import com.example.everyteam.domain.User;
import com.example.everyteam.dto.JsonResponse;
import com.example.everyteam.dto.PostResponse;
import com.example.everyteam.dto.TeamRequest;
import com.example.everyteam.dto.TeamResponse;
import com.example.everyteam.service.PostService;
import com.example.everyteam.service.TeamService;
import com.example.everyteam.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TeamContoller {

    private final JwtService jwtService;
    private final TeamService teamService;
    private final UserService userService;
    private final PostService postService;



    @PostMapping("/team")
    public ResponseEntity<JsonResponse> createTeam(@RequestPart("team") TeamRequest.createTeam req, @RequestPart("image")MultipartFile file){
        String userId = jwtService.resolveToken();
        User user = userService.getUser(userId);

        String teamCode = teamService.createTeam(user, req, file);

        return ResponseEntity.ok(new JsonResponse(true, 200, "createTeam", teamCode));
    }

    @PostMapping("/team/topImage/{teamCode}")
    public ResponseEntity<JsonResponse> updateTopImage(@PathVariable String teamCode,@RequestPart("image")MultipartFile file){
        String userId = jwtService.resolveToken();
        User user = userService.getUser(userId);
        teamService.UserOnTeam(teamCode, userId);
        Team team = teamService.getTeam(teamCode);
        teamService.updateTopImage(team, file);

        return ResponseEntity.ok(new JsonResponse(true, 200, "createTeam", teamCode));
    }

    @PostMapping("/team/{teamCode}")
    public ResponseEntity<JsonResponse> joinTeam(@PathVariable String teamCode){
        String userId = jwtService.resolveToken();
        User user = userService.getUser(userId);
        String code = teamService.joinTeam(user, teamCode);

        return ResponseEntity.ok(new JsonResponse(true, 200, "joinTeam", code));
    }

    // 유저가 속한 Team의 리스트
    @GetMapping("/team")
    public ResponseEntity<JsonResponse> getUserTeamList(){
        String userId = jwtService.resolveToken();
        User user = userService.getUser(userId);
        TeamResponse.getUserTeamList response = teamService.getUserTeamList(user);


        return ResponseEntity.ok(new JsonResponse(true, 200, "joinTeam", response));
    }


    //TODO : team의 post list 가져오기
    //userList, team info, postList
    //TODO : 생성일, 항목별 mapping 다르게 하기
    //meet, role response 다르게 보내기
    @GetMapping("/team/{teamCode}")
    public ResponseEntity<JsonResponse> getTeamPostList(@PathVariable String teamCode){
        //user validation
        String userId = jwtService.resolveToken();

        //team info
        Team team = teamService.getTeam(teamCode);

        //postlist
        List<PostResponse.postList> postList = postService.getPostList(team);

        //userList
        List<String> teamUserList = teamService.getUserList(team.getCode());


        return ResponseEntity.ok(
                new JsonResponse(true, 200, "getTeamPostListByCategory", new TeamResponse.getTeamPostList(team, teamUserList, postList)));
    }


//    @PostMapping("/testTeam")
//    public void testUserOnTeam(@RequestParam String teamCode, @RequestParam String userId){
//        teamService.UserOnTeam(teamCode, userId);
//    }


}
