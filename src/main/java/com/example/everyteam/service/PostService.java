package com.example.everyteam.service;

import com.example.everyteam.config.exception.BadRequestException;
import com.example.everyteam.domain.Post;
import com.example.everyteam.domain.Role;
import com.example.everyteam.domain.Team;
import com.example.everyteam.domain.User;
import com.example.everyteam.dto.post.PostRequest;
import com.example.everyteam.dto.post.PostResponse;
import com.example.everyteam.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.everyteam.config.exception.ErrorResponseStatus.*;

@Service
@RequiredArgsConstructor
public class PostService {
    private final UserService userService;
    private final PostRepository postRepository;
    private final RoleService roleService;

    public Post createPost(Post post) {
        postRepository.save(post);
        return post;
    }


    public List<PostResponse.postRes> getPostList(Team team){
        //post 리스트
        List<Post> postList = postRepository.findAllByTeam(team.getTeamIdx());
        List<PostResponse.postRes> response = postList.stream()
                .map(p-> PostResponse.postRes.builder()
                        .userId(p.getUser().getId())
                        .category(p.getCategory())
                        .content(p.getContent())
                        .title(p.getTitle())
                        .createdAt(p.getCreatedAt())
                        .build()).collect(Collectors.toList());
        return response;
   }

    public List<Object> getPostListByDate(Team team) {
        List<Post> postList = postRepository.findAllByTeam(team.getTeamIdx());
        List<Object> response = new ArrayList<>();

        for(Post post : postList){
            if(post.getCategory().equals("ROLE")){
                System.out.println(post.getContent());
                List<Role> roleList = roleService.getPostRoleList(post.getContent());
                System.out.println(roleList);
//                response.add(new PostResponse.postRoleRes(post,roleList));
            }else{
                response.add(new PostResponse.postRes(post));
            }
        }

        return response;
    }

    ///////TODO : test Post List
    public List<Post> getAllPostList() {
        return postRepository.findAll();
    }


    public Post getPostByMeetCode(String meetCode) {
        return postRepository.findByContent(meetCode).orElseThrow(()->new BadRequestException(MEET_POST_ERROR));
    }

    public void updatePostByMeet(Post post) {
        postRepository.save(post);
    }
}
