package com.example.everyteam.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Table(name = "user")
@NoArgsConstructor
@Entity
public class User extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_idx")
    private Long userIdx;

    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String pwd;

    @Column(nullable = false)
    private String color;

    @Builder
    public User(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
        this.color = "basic";
    }

    public void setColor(String color) {
        this.color = color;
    }
}
