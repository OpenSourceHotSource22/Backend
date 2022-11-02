package com.example.everyteam.controller;

import com.example.everyteam.config.JwtService;
import com.example.everyteam.domain.User;
import com.example.everyteam.dto.JsonResponse;
import com.example.everyteam.dto.UserRequest;
import com.example.everyteam.dto.UserResponse;
import com.example.everyteam.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;


    @PostMapping("/join")
    public ResponseEntity<JsonResponse> joinUser(@RequestBody UserRequest.join req){
        userService.joinUser(req);
        return ResponseEntity.ok(new JsonResponse(true, 200, "joinUser", null));
    }

    @PostMapping("/login")
    public ResponseEntity<JsonResponse> loginUser(@RequestBody UserRequest.login req){
        String userId = userService.login(req);
        String token = jwtService.createToken(userId);

        return ResponseEntity.ok(new JsonResponse(true, 200, "loginUser", new UserResponse.login(userId, token)));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<JsonResponse> getUser(@PathVariable String userId){
        User user = userService.getUser(userId);
        return ResponseEntity.ok(new JsonResponse(true, 200, "getUser", user));
    }
}
