package com.example.everyteam.dto.post;

import com.example.everyteam.domain.Meet;
import com.example.everyteam.domain.Post;
import com.example.everyteam.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostResponse {
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class postRes{
        private String userId;
        private String title;
        private String content;
        private String createdAt;
        private String category;

        @Builder
        public postRes(String userId, String title, String content, LocalDateTime createdAt, String category) {
            this.userId = userId;
            this.title = title;
            this.content = content;
            this.createdAt = createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            this.category = category;
        }
        @Builder
        public postRes(Post post){
            this.userId = post.getUser().getId();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.createdAt = post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            this.category = post.getCategory();
        }

    }


    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class postRoleRes {
        private String userId;
        private String title;
        private Map<String, String> roles = new HashMap<>();
        private String createdAt;
        private String category;
        @Builder
        public postRoleRes(Post post, List<Role> roleList){
            this.userId = post.getUser().getId();
            this.title = post.getTitle();
            for(Role role : roleList){
                this.roles.put(role.getUser(), role.getRole());
            }
            this.createdAt = post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            this.category = post.getCategory();
        }
    }

    @NoArgsConstructor
    @Data
    public static class resCategory {
        private List<postRes> post;
        private List<postRes> meet;
        private List<Object> role;

        public resCategory(List<postRes> post, List<postRes> meet, List<Object> role) {
            this.post = post;
            this.meet = meet;
            this.role = role;
        }
    }
}
