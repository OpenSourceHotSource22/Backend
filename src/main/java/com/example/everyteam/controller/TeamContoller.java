package com.example.everyteam.controller;

import com.example.everyteam.config.JwtService;
import com.example.everyteam.domain.Team;
import com.example.everyteam.domain.User;
import com.example.everyteam.dto.JsonResponse;
import com.example.everyteam.dto.post.PostResponse;
import com.example.everyteam.dto.team.TeamRequest;
import com.example.everyteam.dto.team.TeamResponse;
import com.example.everyteam.service.PostService;
import com.example.everyteam.service.TeamService;
import com.example.everyteam.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
public class TeamContoller {

    private final JwtService jwtService;
    private final TeamService teamService;
    private final UserService userService;
    private final PostService postService;


    @PostMapping("/team/create")
    public ResponseEntity<JsonResponse> createTeam(@RequestPart("team") TeamRequest.createTeam req, @RequestPart("image")MultipartFile file){
        String userId = jwtService.resolveToken();
        User user = userService.getUser(userId);

        String teamCode = teamService.createTeam(user, req, file);

        return ResponseEntity.ok(new JsonResponse(true, 200, "createTeam", teamCode));
    }

    @PutMapping("/team/updateTopImage")
    public ResponseEntity<JsonResponse> updateTopImage(@RequestPart("team") TeamRequest.updateTeam req,@RequestPart("image")MultipartFile file){
        String userId = jwtService.resolveToken();
        teamService.UserOnTeam(req.getTeamCode(), userId);

        Team team = teamService.getTeam(req.getTeamCode());
        teamService.updateTopImage(team, file);

        return ResponseEntity.ok(new JsonResponse(true, 200, "updateTopImage", team.getCode()));
    }

    @PostMapping("/team/join")
    public ResponseEntity<JsonResponse> joinTeam(@RequestBody TeamRequest.join req){
        String userId = jwtService.resolveToken();
        User user = userService.getUser(userId);
        String code = teamService.joinTeam(user, req.getTeamCode());

        return ResponseEntity.ok(new JsonResponse(true, 200, "joinTeam", code));
    }



    //TODO : team의 post list 가져오기
    //userList, team info, postList
    //TODO : 생성일 mapping 다르게 하기
    //meet, role response 다르게 보내기
    @GetMapping("/team/date")
    public ResponseEntity<JsonResponse> getTeamPostByDate(@RequestParam String teamCode){
        //user validation
        String userId = jwtService.resolveToken();

        //team info
        teamService.UserOnTeam(teamCode,userId);
        Team team = teamService.getTeam(teamCode);

        //postlist
        List<PostResponse.postList> postList = postService.getPostList(team);

        //userList
        List<String> teamUserList = teamService.getUserList(team.getCode());


        return ResponseEntity.ok(
                new JsonResponse(true, 200, "getTeamPostByDate", new TeamResponse.getTeamPostList(team, teamUserList, postList)));
    }


    @GetMapping("/team/category")
    public ResponseEntity<JsonResponse> getTeamPostByCategory(@RequestParam String teamCode){
        //user validation
        String userId = jwtService.resolveToken();

        //team info
        teamService.UserOnTeam(teamCode,userId);
        Team team = teamService.getTeam(teamCode);

        //postlist
        List<PostResponse.postList> postList = postService.getPostList(team);

        //userList
        List<String> teamUserList = teamService.getUserList(team.getCode());


        return ResponseEntity.ok(
                new JsonResponse(true, 200, "getTeamPostListByCategory", new TeamResponse.getTeamPostList(team, teamUserList, postList)));
    }


    @GetMapping("/test/getTeamList")
    public Object getTeamList(){
        return teamService.getTeamList();
    }


}
