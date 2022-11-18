package com.example.everyteam.service;

import com.example.everyteam.config.exception.BadRequestException;
import com.example.everyteam.config.exception.ErrorResponseStatus;
import com.example.everyteam.domain.Meet;
import com.example.everyteam.domain.MeetTime;
import com.example.everyteam.domain.Post;
import com.example.everyteam.domain.User;
import com.example.everyteam.dto.meet.MeetRequest;
import com.example.everyteam.dto.meet.MeetResponse;
import com.example.everyteam.dto.post.PostRequest;
import com.example.everyteam.repository.MeetRepository;
import com.example.everyteam.repository.MeetTimeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.example.everyteam.config.exception.ErrorResponseStatus.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class MeetService {
    private final MeetRepository meetRepository;
    private final MeetTimeRepository meetTimeRepository;


    //createDate
    public void createDate(Post post, MeetRequest.createDate req, String meetCode) {
        for(LocalDate date : req.getDate()){
            System.out.println(date);
            Meet meet = Meet.builder().code(meetCode).date(date).post(post).build();
            meetRepository.save(meet);
        }
    }


    //TODO : 이미 시간을 등록한 유저의 경우 유저의 결과 보여주기, 새로 등록한 유저의 경우 null
    public List<MeetResponse.getMeetDate> getMeetDate(String meetCode, String userId){
        validMeetCode(meetCode);
        List<Meet> dates = meetRepository.findMeetByCode(meetCode);
        List<MeetResponse.getMeetDate> response = new ArrayList<>();
        for(Meet meet : dates){
            try{
                MeetTime time = meetTimeRepository.findByMeetandUser(meet.getDate(),userId);
                response.add(new MeetResponse.getMeetDate(meet,time));
            }catch (NullPointerException e){
                return null;
            }

        }
        return response;
    }

    public void validMeetCode(String meetCode){
        int countCode = meetRepository.countByCode(meetCode);
        if(countCode<1)throw new BadRequestException(MEET_CODE_ERROR);
    }

    @Transactional
    public void updateTime(MeetRequest.updateTime req, User user) {
        validMeetCode(req.getMeetCode());
        List<Meet> meetList = meetRepository.findAllByCode(req.getMeetCode());

        //이미 유저가 등록한 meet이 있을 경우 delete
        List<MeetTime> meetTimeList =  meetTimeRepository.findAllByMeetandUser(req.getMeetCode(), user.getId());
        if(!meetTimeList.isEmpty()){
            for(MeetTime m:meetTimeList){
                meetTimeRepository.delete(m);
            }
        }

        //TODO : meet에 등록되지 않은 잘못된 날짜 입력시 exception
//        for (int i = 0; i < req.getMeet().size(); i++) {
//            if (meetList.contains(req.getMeet().get(i).get("date"))) ;
//            else throw new BadRequestException(MEET_DATE_ERROR);
//        }

        for(Meet meet :meetList){
            for(int i=0;i<req.getMeet().size();i++){
                String date = String.valueOf(req.getMeet().get(i).get("date"));
                LocalDate datetime = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
                if(meet.getDate().isEqual(datetime)){
                    String time = String.valueOf(req.getMeet().get(i).get("time"));
                    MeetTime addTime = MeetTime.builder().meet(meet).time(time).user(user).build();
                    meetTimeRepository.save(addTime);
                }
            }
        }
    }

    public String getResultTime(MeetRequest.updateTime req, User user) {
        return null;
    }


}
