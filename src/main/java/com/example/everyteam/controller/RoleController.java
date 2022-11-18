package com.example.everyteam.controller;


import com.example.everyteam.config.JwtService;
import com.example.everyteam.domain.Post;
import com.example.everyteam.domain.Team;
import com.example.everyteam.domain.User;
import com.example.everyteam.dto.JsonResponse;
import com.example.everyteam.dto.meet.MeetRequest;
import com.example.everyteam.dto.meet.MeetResponse;
import com.example.everyteam.dto.post.PostRequest;
import com.example.everyteam.dto.role.RoleRequest;
import com.example.everyteam.service.PostService;
import com.example.everyteam.service.RoleService;
import com.example.everyteam.service.TeamService;
import com.example.everyteam.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/role")
@RequiredArgsConstructor
@RestController
public class RoleController {
    private final JwtService jwtService;
    private final UserService userService;
    private final TeamService teamService;
    private final PostService postService;
    private final RoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<JsonResponse> createRole(@RequestBody RoleRequest.createRole req){
        String userId = jwtService.resolveToken();

        User user = userService.getUser(userId);

        teamService.UserOnTeam(req.getTeamCode(),userId);
        Team team = teamService.getTeam(req.getTeamCode());

        String roleCode = teamService.randomCode();

        //TODO : content에 meetCode url로 담을지?
        PostRequest.createPost newPost = PostRequest.createPost.builder()
                .teamCode(req.getTeamCode()).title(req.getTitle()).content(roleCode).category("Meet").build();
        Post post = postService.createPost(user, team, newPost);

        roleService.createRole(post, req,roleCode);

        return ResponseEntity.ok(new JsonResponse(true, 200, "createDate", null));
    }

    @GetMapping("/userList")
    public ResponseEntity<JsonResponse> getTeamUserList(@RequestBody RoleRequest.getUserList req){
        String userId = jwtService.resolveToken();

        teamService.UserOnTeam(req.getTeamCode(),userId);

        List<String> response = teamService.getUserList(req.getTeamCode());
        return ResponseEntity.ok(
                new JsonResponse(true, 200, "getTeamUserList", response));
    }
}
