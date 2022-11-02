package com.example.everyteam.repository;


import com.example.everyteam.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByCode(String teamCode);

    List<String> findUserByCode(String teamCode);
}
