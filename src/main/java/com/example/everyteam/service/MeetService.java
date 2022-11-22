package com.example.everyteam.service;

import com.example.everyteam.config.exception.BadRequestException;
import com.example.everyteam.domain.Meet;
import com.example.everyteam.domain.MeetTime;
import com.example.everyteam.domain.Post;
import com.example.everyteam.domain.User;
import com.example.everyteam.dto.meet.MeetRequest;
import com.example.everyteam.dto.meet.MeetResponse;
import com.example.everyteam.repository.MeetRepository;
import com.example.everyteam.repository.MeetTimeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    public List<Object> getMeetDate(String meetCode, String userId){
        validMeetCode(meetCode);
        List<Meet> dates = meetRepository.findMeetByCode(meetCode);
        List<Object> response = new ArrayList<>();
        for(Meet meet : dates){
            try{
                MeetTime time = meetTimeRepository.findByMeetandUser(meet.getDate(),meet.getCode(),userId);
                response.add(new MeetResponse.getMeetDateandTime(meet,time));
            }catch (NullPointerException e){
                response.add(new MeetResponse.getMeetDate(meet));
            }
        }
        return response;
    }
    public List<LocalDate> getAllMeetDate(String meetCode){
        List<LocalDate> meetDateList = meetRepository.findAllDateByCode(meetCode);
        return meetDateList;
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

    //TODO : 유저 [date : 시간]
    public List<MeetResponse.getResultTime> getResultTime(MeetRequest.getResultTime req, User user) {
        validMeetCode(req.getMeetCode());
        List<MeetResponse.getResultTime> response = new ArrayList<>();

        List<Meet> meetList = meetRepository.findAllByCode(req.getMeetCode());


        List<String> meetUserList = meetTimeRepository.findAllByUserCode(req.getMeetCode());

        List<String> existUser = new ArrayList<>();


        //중복 유저 거르기
        for(int i=0;i<meetUserList.size();i++){
            String userId = meetUserList.get(i);
            if(!existUser.contains(userId)) existUser.add(userId);
        }

        //
        for(String userId : existUser){
            List<MeetTime> time = meetTimeRepository.findAllByUserandCode(userId, req.getMeetCode());
            response.add(new MeetResponse.getResultTime(meetList.get(0), time));
        }


        return response;
    }



    ///////TODO : test Meet List
    public List<Meet> getAllMeetList() {
        return meetRepository.findAll();
    }

    public List<MeetTime> getAllMeetTimeList() {
        return meetTimeRepository.findAll();
    }

    public void isUserinMeet(String meetCode, String userId) {
        validMeetCode(meetCode);
        int countUserMeet = meetTimeRepository.countByCodeandUser(meetCode, userId);
        if(countUserMeet<1)throw new BadRequestException(MEET_USER_NOT_FOUND);
    }
}
