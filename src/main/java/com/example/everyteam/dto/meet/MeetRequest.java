package com.example.everyteam.dto.meet;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class MeetRequest {
    @Data
    public static class createDate {
        private String teamCode;
        private String title;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate[] date;
    }

    @Data
    public static class updateTime {
        private String teamCode;
        private String meetCode;
        private List<Map<String, Object>> meet;
    }

    @Data
    public static class getMeetDate {
        private String teamCode;
        private String meetCode;
    }


    @Data
    public static class getResultTime {
        private String teamCode;
        private String meetCode;
    }

    @Data
    public static class updatePostMeet {
        private String teamCode;
        private String meetCode;
        private String content;
    }
}
