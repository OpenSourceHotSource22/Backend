package com.example.everyteam.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Table(name = "team")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_idx")
    private Long teamIdx;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String code;

    @Column(nullable = true)
    private String imgUrl;

    @Column(nullable = false)
    private String description;

    @Column(nullable = true)
    private String topImgUrl;

    @Builder
    public Team(String name, String code, String imgUrl, String description) {
        this.name = name;
        this.code = code;
        this.imgUrl = imgUrl;
        this.description = description;
    }

    public void setTopImgUrl(String topImgUrl) {
        this.topImgUrl = topImgUrl;
    }
}
