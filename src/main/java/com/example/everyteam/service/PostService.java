//package com.example.everyteam.service;
//
//import com.example.everyteam.domain.Post;
//import com.example.everyteam.domain.Team;
//import com.example.everyteam.domain.User;
//import com.example.everyteam.dto.PostRequest;
//import com.example.everyteam.repository.PostRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class PostService {
//
//    private final PostRepository postRepository;
//
//    public Long createPost(User user, Team team, PostRequest.createPost req) {
//        Post post = Post.builder()
//                .user(user)
//                .team(team)
//                .category(req.getCategory())
//                .content(req.getContent())
//                .title(req.getTitle())
//                .build();
//
//    }
//}
