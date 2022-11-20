package com.example.everyteam.repository;

import com.example.everyteam.domain.Post;
import com.example.everyteam.domain.Team;
//import com.sun.tools.doclint.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "select p from Post as p where p.team.teamIdx =?1")
    List<Post> findAllByTeam(Long team);

    @Query(value = "select p from Post as p where p.content=?1")
    Optional<Post> findByContent(String meetCode);
}
