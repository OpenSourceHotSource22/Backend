package com.example.everyteam.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@Table(name = "role")
@NoArgsConstructor
@Entity
public class Role extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_idx")
    private Long roleIdx;


    @ManyToOne
    @JoinColumn(name = "post")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;

//    @ManyToOne
//    @JoinColumn(name = "user")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private User user;
    private String user;

    private String role;

    private String code;

    @Builder
    public Role(Post post, String user, String role,String code) {
        this.post = post;
        this.user = user;
        this.role = role;
        this.code = code;
    }
}
