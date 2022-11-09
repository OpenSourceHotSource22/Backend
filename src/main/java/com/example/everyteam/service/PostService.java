package com.example.everyteam.service;

import com.example.everyteam.domain.Post;
import com.example.everyteam.domain.Team;
import com.example.everyteam.domain.User;
import com.example.everyteam.dto.PostRequest;
import com.example.everyteam.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
//    public List<Post> getPostList(Team team){
//
//        //team 정보
//
//        //post 리스트
//        List<Post> postList = postRepository.findAllByTeam();
//
//        return postList;
//   }

//    public Post getPost(){
//
//    }
}
