package com.example.everyteam.controller;


import com.example.everyteam.config.JwtService;
import com.example.everyteam.domain.Team;
import com.example.everyteam.domain.User;
import com.example.everyteam.dto.JsonResponse;
import com.example.everyteam.dto.post.PostRequest;
import com.example.everyteam.service.PostService;
import com.example.everyteam.service.TeamService;
import com.example.everyteam.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostContoller {
    private final PostService postService;
    private final JwtService jwtService;
    private final UserService userService;
    private final TeamService teamService;


    //TODO : 게시글 생성, 카테고리 별로 구분
    @ApiImplicitParams({@ApiImplicitParam(name="X-AUTH-TOKEN",value = "HttpServletRequest", required = true, dataType = "string",paramType = "header")})
    @PostMapping("/post")
    public ResponseEntity<JsonResponse> createPost(@RequestBody PostRequest.createPost req){
        String userId = jwtService.resolveToken();
        User user = userService.getUser(userId);
        Team team = teamService.getTeam(req.getTeamCode());
        postService.createPost(user, team, req);

        return ResponseEntity.ok(new JsonResponse(true, 200, "createTeam", team.getCode()));
    }


//    //TODO : post 하나만 볼 때는...?
//    @PostMapping("/post/{postIdx}")
//    public ResponseEntity<JsonResponse> getPost(HttpServletRequest request, @PathVariable Long postIdx){
//        String userId = jwtService.resolveToken(request);
//        User user = userService.getUser(userId);
//
//        String postIdx = postService.getPost(user, postIdx);
//
//        return ResponseEntity.ok(new JsonResponse(true, 200, "createTeam", teamCode));
//    }





    @GetMapping("/test/getPostList")
    public Object getPostList(){
        return postService.getAllPostList();
    }


}
