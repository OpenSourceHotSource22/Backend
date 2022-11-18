package com.example.everyteam.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@Table(name = "meet_time")
@NoArgsConstructor
@Entity
public class MeetTime extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meet_time_idx")
    private Long meetTimeIdx;

    @ManyToOne
    @JoinColumn(name = "user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne
    @JoinColumn(name = "date")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Meet meet;

    //time은 시간을 string 값으로 저장, front에서 split하여 보여줌
    private String time;

    @Builder
    public MeetTime(User user, Meet meet, String time) {
        this.user = user;
        this.meet = meet;
        this.time = time;
    }
}