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

    @Builder
    public Team(String name, String code) {
        this.name = name;
        this.code = code;
    }
}
