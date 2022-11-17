package com.example.everyteam.service;

import com.example.everyteam.domain.Post;
import com.example.everyteam.domain.Team;
import com.example.everyteam.domain.User;
import com.example.everyteam.dto.PostRequest;
import com.example.everyteam.dto.PostResponse;
import com.example.everyteam.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final UserService userService;
    private final PostRepository postRepository;

    public void createPost(User user, Team team, PostRequest.createPost req) {
        Post post = Post.builder()
                .user(user)
                .team(team)
                .category(req.getCategory())
                .content(req.getContent())
                .title(req.getTitle())
                .build();
        postRepository.save(post);
    }


    public List<PostResponse.postList> getPostList(Team team){
        //post 리스트
        List<Post> postList = postRepository.findAllByTeam(team.getTeamIdx());
        List<PostResponse.postList> response = postList.stream()
                .map(p-> PostResponse.postList.builder()
                        .userId(p.getUser().getId())
                        .category(p.getCategory())
                        .content(p.getContent())
                        .title(p.getTitle())
                        .createdAt(p.getCreatedAt())
                        .build()).collect(Collectors.toList());
        return response;
   }

//    public Post getPost(){
//
//    }
}
