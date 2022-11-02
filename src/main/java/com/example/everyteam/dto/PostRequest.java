package com.example.everyteam.dto;

import com.example.everyteam.domain.Team;
import com.example.everyteam.domain.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class PostRequest {

    public static class createPost{
        private Long user;

        private Long team;

        private String title;

        private String content;

        private String category;
    }
}
