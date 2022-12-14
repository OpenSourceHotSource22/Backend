package com.example.everyteam.dto.post;

import com.example.everyteam.domain.Team;
import com.example.everyteam.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class PostRequest {
    @NoArgsConstructor
    @Data
    public static class createPost{
        private String teamCode;
        private String title;
        private String content;

        @Builder
        public createPost(String teamCode, String title, String content) {
            this.teamCode = teamCode;
            this.title = title;
            this.content = content;
        }
    }

}
