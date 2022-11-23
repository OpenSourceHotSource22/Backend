package com.example.everyteam.controller;

import com.example.everyteam.config.JwtService;
import com.example.everyteam.domain.Post;
import com.example.everyteam.domain.Team;
import com.example.everyteam.domain.User;
import com.example.everyteam.dto.JsonResponse;
import com.example.everyteam.dto.meet.MeetRequest;
import com.example.everyteam.dto.meet.MeetResponse;
import com.example.everyteam.dto.post.PostRequest;
import com.example.everyteam.service.MeetService;
import com.example.everyteam.service.PostService;
import com.example.everyteam.service.TeamService;
import com.example.everyteam.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequestMapping("/meet")
@RequiredArgsConstructor
@RestController
public class MeetController {
    private final JwtService jwtService;
    private final TeamService teamService;
    private final UserService userService;
    private final PostService postService;
    private final MeetService meetService;

    //처음 유저가 select date
    @PostMapping("/createDate")
    public ResponseEntity<JsonResponse> createDate(@RequestBody MeetRequest.createDate req){
        String userId = jwtService.resolveToken();

        User user = userService.getUser(userId);

        teamService.UserOnTeam(req.getTeamCode(),userId);
        Team team = teamService.getTeam(req.getTeamCode());

        String meetCode = teamService.randomCode();

        //TODO : content에 meetCode url로 담을지?

        Post postForm = Post.builder().user(user).team(team).title(req.getTitle()).content(meetCode).category("MEET").build();
        Post post = postService.createPost(postForm);

        meetService.createDate(post, req, meetCode);

        return ResponseEntity.ok(new JsonResponse(true, 200, "createDate", new MeetResponse.createDate(team.getCode(), meetCode)));
    }

    @PostMapping("/getDate")
    public ResponseEntity<JsonResponse> getMeetDate(@RequestBody MeetRequest.getMeetDate req){
        String userId = jwtService.resolveToken();
        teamService.UserOnTeam(req.getTeamCode(), userId);

        List<Object> dates = meetService.getMeetDate(req.getMeetCode(), userId);

        MeetResponse.getMeetDateRes response = new MeetResponse.getMeetDateRes(userId,dates);

        return ResponseEntity.ok(new JsonResponse(true, 200, "getMeetDate", response));
    }

    @PutMapping ("/updateTime")
    public ResponseEntity<JsonResponse> updateTime(@RequestBody MeetRequest.updateTime req){
        String userId = jwtService.resolveToken();
        User user = userService.getUser(userId);

        meetService.updateTime(req,user);

        MeetResponse.updateTime response = new MeetResponse.updateTime(req.getTeamCode(),req.getMeetCode());
        return ResponseEntity.ok(new JsonResponse(true, 200, "updateTime", response));

    }

    @PostMapping ("/getResultTime")
    public ResponseEntity<JsonResponse> getResultTime(@RequestBody MeetRequest.getResultTime req){
        String userId = jwtService.resolveToken();
        User user = userService.getUser(userId);

        List<MeetResponse.getResultTime> meetList = meetService.getResultTime(req,user);

        Post post = postService.getPostByMeetCode(req.getMeetCode());
        List<LocalDate> meetDate = meetService.getAllMeetDate(req.getMeetCode());
        MeetResponse.ResultRes response = new MeetResponse.ResultRes(post, meetList, meetDate);

        return ResponseEntity.ok(new JsonResponse(true, 200, "getResultTime", response));
    }

    @PostMapping ("/updatePostMeet")
    public ResponseEntity<JsonResponse> updatePostMeet(@RequestBody MeetRequest.updatePostMeet req){
        String userId = jwtService.resolveToken();
        User user = userService.getUser(userId);
        teamService.UserOnTeam(req.getTeamCode(), userId);

        meetService.isUserinMeet(req.getMeetCode(), userId);

        Post post = postService.getPostByMeetCode(req.getMeetCode());
        post.setContent("result/"+req.getContent());
        postService.updatePostByMeet(post, user);

        return ResponseEntity.ok(new JsonResponse(true, 200, "updatePostMeet", req.getTeamCode()));

    }


    @GetMapping("/test/getMeetList")
    public Object getMeetList(){
        return meetService.getAllMeetList();
    }

    @GetMapping("/test/getMeetTimeList")
    public Object getMeetTimeList(){
        return meetService.getAllMeetTimeList();
    }

}
