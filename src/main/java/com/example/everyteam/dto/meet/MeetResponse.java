package com.example.everyteam.dto.meet;

import com.example.everyteam.domain.Meet;
import com.example.everyteam.domain.MeetTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
        private LocalDate date;
        private String time;

        public getMeetDate(Meet meet, MeetTime time) {
            this.date = meet.getDate();
            this.time = time.getTime();
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class getMeetDateRes {
        private String userId;
        private List<getMeetDate> date;
    }
}
