package com.example.everyteam.repository;

import com.example.everyteam.domain.MeetTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MeetTimeRepository extends JpaRepository<MeetTime, Long> {
    @Query("SELECT m FROM MeetTime m where m.meet.code=?1 and m.user.id=?2")
    List<MeetTime> findAllByMeetandUser(String meetCode, String id);

    @Query("SELECT m FROM MeetTime m WHERE m.meet.date=?1 and m.user.id=?2")
    MeetTime findByMeetandUser(LocalDate date, String userId);
}