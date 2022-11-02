package com.example.everyteam.controller;

import com.example.everyteam.config.JwtService;
import com.example.everyteam.domain.Belong;
import com.example.everyteam.domain.User;
import com.example.everyteam.dto.JsonResponse;
import com.example.everyteam.dto.TeamRequest;
import com.example.everyteam.service.TeamService;
import com.example.everyteam.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TeamContoller {

    private final JwtService jwtService;
    private final TeamService teamService;
    private final UserService userService;
//    private final PostService postService;

    @PostMapping("/team")
    public ResponseEntity<JsonResponse> createTeam(HttpServletRequest request, @RequestBody TeamRequest.createTeam req){
        String userId = jwtService.resolveToken(request);
        User user = userService.getUser(userId);
        String teamCode = teamService.createTeam(user, req);

        return ResponseEntity.ok(new JsonResponse(true, 200, "createTeam", teamCode));
    }

    @PostMapping("/team/{teamCode}")
    public ResponseEntity<JsonResponse> joinTeam(HttpServletRequest request, @PathVariable String teamCode){
        String userId = jwtService.resolveToken(request);
        User user = userService.getUser(userId);
        String code = teamService.joinTeam(user, teamCode);

        return ResponseEntity.ok(new JsonResponse(true, 200, "joinTeam", code));
    }

    // 유저가 속한 Team의 리스트
    @GetMapping("/team")
    public ResponseEntity<JsonResponse> getUserTeamList(HttpServletRequest request){
        String userId = jwtService.resolveToken(request);
        User user = userService.getUser(userId);
        List<Belong> teamList = teamService.getUserTeamList(user);


        return ResponseEntity.ok(new JsonResponse(true, 200, "joinTeam", teamList));
    }


//    //TODO : team의 post list 가져오기
//    //userList, team info, postList
//    @GetMapping("/team/{teamCode}")
//    public ResponseEntity<JsonResponse> getTeamPostList(HttpServletRequest request, @PathVariable String teamCode){
//        String userId = jwtService.resolveToken(request);
//        User user = userService.getUser(userId);
//        List<Post> postList = postService.getTeamPostList(teamCode);
//        Team team = teamService.getTeam(teamCode);
//        List<String> teamUserList = teamService.getUserList(teamCode);
//
//
//        return ResponseEntity.ok(
//                new JsonResponse(true, 200, "getTeamPostListByCategory", new TeamResponse.getTeamPostList(team, teamUserList, postList)));
//    }
//


}
