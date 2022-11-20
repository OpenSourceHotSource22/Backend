package com.example.everyteam.repository;

import com.example.everyteam.domain.Meet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MeetRepository extends JpaRepository<Meet, Long> {

//    @Query("SELECT m.date FROM Meet m where m.code=?1")
//    List<String> findDateByCode(String meetCode);


    @Query("SELECT count(m.date) FROM Meet m where m.code=?1")
    int countByCode(String meetCode);

    List<Meet> findAllByCode(String meetCode);

    List<Meet> findMeetByCode(String meetCode);


}
