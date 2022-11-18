package com.example.everyteam.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Table(name = "meet")
@NoArgsConstructor
@Entity
public class Meet extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meet_idx")
    private Long meetIdx;

    @ManyToOne
    @JoinColumn(name = "post")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;

    private LocalDate date;

    private String code;

    @Builder
    public Meet(Post post, LocalDate date, String code) {
        this.post = post;
        this.date = date;
        this.code = code;
    }
}
