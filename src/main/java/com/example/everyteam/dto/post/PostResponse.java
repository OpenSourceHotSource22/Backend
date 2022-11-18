package com.example.everyteam.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PostResponse {
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class postList{
        private String userId;
        private String title;
        private String content;
        private String createdAt;
        private String category;

        @Builder
        public postList(String userId, String title, String content, LocalDateTime createdAt, String category) {
            this.userId = userId;
            this.title = title;
            this.content = content;
            this.createdAt = createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            this.category = category;
        }
    }
}
