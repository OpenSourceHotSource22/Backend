package com.example.everyteam.dto.meet;

import com.example.everyteam.domain.Meet;
import com.example.everyteam.domain.MeetTime;
import com.example.everyteam.domain.Post;
import com.example.everyteam.dto.post.PostResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MeetResponse {
    @Data
    public static class createDate {
        private String teamCode;
        private String meetCode;

        public createDate(String teamCode, String meetCode) {
            this.meetCode = meetCode;
            this.teamCode = teamCode;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class updateTime {
        private String teamCode;
        private String meetCode;
    }

    @Data
    public static class getMeetDate {
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate date;

        public getMeetDate(Meet meet) {
            this.date = meet.getDate();
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class getMeetDateRes {
        private String userId;
        private List<?> date;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class getMeetDateandTime {
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate date;
        private String time;

        public getMeetDateandTime(Meet meet, MeetTime time) {
            this.date = meet.getDate();
            this.time = time.getTime();
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class getResultTime {
        private String userId;
        private String postTitle;
        private List<getMeetDateandTime> meet = new ArrayList<>();

        public getResultTime(Meet meet, List<MeetTime> meetTime) {
            this.userId = meetTime.get(0).getUser().getId();
            for(MeetTime time : meetTime){
                this.meet.add(new getMeetDateandTime(meet,time));
            }
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResultRes {
        private Object post;
        private List<getResultTime> meetList;

        public ResultRes(Post post, List<getResultTime> meetList) {
            this.post = new PostResponse.postRes(post);
            this.meetList = meetList;
        }
    }
}
