package com.example.everyteam.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@Table(name = "post")
@NoArgsConstructor
@Entity
public class Post extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_idx")
    private Long postIdx;

    @ManyToOne
    @JoinColumn(name = "user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne
    @JoinColumn(name = "team")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Team team;

    @Column(nullable = false)
    private String title;

    private String content;

    //schedule, post, role
    @Column(nullable = false)
    private String category;

    @Builder
    public Post(User user, Team team, String title, String content, String category) {
        this.user = user;
        this.team = team;
        this.title = title;
        this.content = content;
        this.category = category;
    }
}
