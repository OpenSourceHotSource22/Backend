package com.example.everyteam.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@Table(name = "belong")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Belong extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "belong_idx")
    private Long belongIdx;

    @ManyToOne
    @JoinColumn(name = "user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne
    @JoinColumn(name = "team")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Team team;

    @Builder
    public Belong(User user, Team team) {
        this.user = user;
        this.team = team;
    }
}
