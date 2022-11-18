package com.example.everyteam.controller;

import com.example.everyteam.config.JwtService;
import com.example.everyteam.domain.*;
import com.example.everyteam.dto.JsonResponse;
import com.example.everyteam.dto.meet.MeetRequest;
import com.example.everyteam.dto.meet.MeetResponse;
import com.example.everyteam.dto.post.PostRequest;
import com.example.everyteam.repository.MeetRepository;
import com.example.everyteam.service.MeetService;
import com.example.everyteam.service.PostService;
import com.example.everyteam.service.TeamService;
import com.example.everyteam.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080") // 추가
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
    @ApiImplicitParams({@ApiImplicitParam(name="X-AUTH-TOKEN",value = "HttpServletRequest", required = true, dataType = "string",paramType = "header")})
    @PostMapping("/createDate")
    public ResponseEntity<JsonResponse> createDate(@RequestBody MeetRequest.createDate req){
        String userId = jwtService.resolveToken();

        User user = userService.getUser(userId);

        teamService.UserOnTeam(req.getTeamCode(),userId);
        Team team = teamService.getTeam(req.getTeamCode());

        String meetCode = teamService.randomCode();

        //TODO : content에 meetCode url로 담을지?
        PostRequest.createPost newPost = PostRequest.createPost.builder()
                .teamCode(req.getTeamCode()).title(req.getTitle()).content(meetCode).category("MEET").build();
        Post post = postService.createPost(user, team, newPost);

        meetService.createDate(post, req, meetCode);

        return ResponseEntity.ok(new JsonResponse(true, 200, "createDate", new MeetResponse.createDate(team.getCode(), meetCode)));
    }

    @ApiImplicitParams({@ApiImplicitParam(name="X-AUTH-TOKEN",value = "HttpServletRequest", required = true, dataType = "string",paramType = "header")})
    @GetMapping("/getDate")
    public ResponseEntity<JsonResponse> getMeetDate(@RequestBody MeetRequest.getMeetDate req){
        String userId = jwtService.resolveToken();
        teamService.UserOnTeam(req.getTeamCode(), userId);

        List<MeetResponse.getMeetDate> dates = meetService.getMeetDate(req.getMeetCode(), userId);

        MeetResponse.getMeetDateRes response = new MeetResponse.getMeetDateRes(userId,dates);

        return ResponseEntity.ok(new JsonResponse(true, 200, "getMeetDate", response));
    }

    @ApiImplicitParams({@ApiImplicitParam(name="X-AUTH-TOKEN",value = "HttpServletRequest", required = true, dataType = "string",paramType = "header")})
    @PutMapping ("/updateTime")
    public ResponseEntity<JsonResponse> updateTime(@RequestBody MeetRequest.updateTime req){
        String userId = jwtService.resolveToken();
        User user = userService.getUser(userId);

        meetService.updateTime(req,user);

        MeetResponse.updateTime response = new MeetResponse.updateTime(req.getTeamCode(),req.getMeetCode());
        return ResponseEntity.ok(new JsonResponse(true, 200, "updateTime", response));

    }

    @ApiImplicitParams({@ApiImplicitParam(name="X-AUTH-TOKEN",value = "HttpServletRequest", required = true, dataType = "string",paramType = "header")})
    @GetMapping ("/getResultTime")
    public ResponseEntity<JsonResponse> getResultTime(@RequestBody MeetRequest.updateTime req){
        String userId = jwtService.resolveToken();
        User user = userService.getUser(userId);

        String meetCode = meetService.getResultTime(req,user);

        return ResponseEntity.ok(new JsonResponse(true, 200, "getResultTime", meetCode));

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
