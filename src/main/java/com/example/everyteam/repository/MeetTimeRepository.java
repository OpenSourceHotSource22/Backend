package com.example.everyteam.repository;

import com.example.everyteam.domain.Meet;
import com.example.everyteam.domain.MeetTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MeetTimeRepository extends JpaRepository<MeetTime, Long> {
    @Query("SELECT m FROM MeetTime m where m.meet.code=?1 and m.user.id=?2")
    List<MeetTime> findAllByMeetandUser(String meetCode, String id);

    @Query("SELECT m FROM MeetTime m WHERE m.meet.date=?1 and m.meet.code=?2 and m.user.id=?3")
    MeetTime findByMeetandUser(LocalDate date, String meetCode, String userId);


    @Query("SELECT m FROM MeetTime m WHERE m.user.id=?1 and m.meet.code=?2")
    List<MeetTime> findAllByUserandCode(String id, String meetCode);

    @Query("SELECT m FROM MeetTime m WHERE m.meet.code=?1")
    List<MeetTime> findAllByCode(String meetCode);


    @Query("SELECT m.user.id FROM MeetTime m WHERE m.meet.code=?1")
    List<String> findAllByUserCode(String meetCode);


    @Query("SELECT count(m.user) FROM MeetTime m where m.meet.code=?1 and m.user.id=?2")
    int countByCodeandUser(String meetCode, String userId);
}
