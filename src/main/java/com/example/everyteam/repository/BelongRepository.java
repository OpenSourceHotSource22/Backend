package com.example.everyteam.repository;

import com.example.everyteam.domain.Belong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BelongRepository extends JpaRepository<Belong, Long> {

    @Query(value = "SELECT b FROM Belong b WHERE b.user.userIdx = ?1")
    List<Belong> findAllByUserIdx(Long userIdx);

    @Query(value = "SELECT COUNT(b.team) FROM Belong b WHERE b.user.userIdx=?1 and b.team.teamIdx=?2")
    Long countTeamCodeByUser(Long userIdx, Long teamIdx);
}
