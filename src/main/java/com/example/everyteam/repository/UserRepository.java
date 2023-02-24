package com.example.everyteam.repository;

import com.example.everyteam.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT count(u.id) FROM User u WHERE u.id=?1")
    int countByUserId(String id);

    @Query(value = "SELECT u FROM User u WHERE u.id=?1")
    Optional<User> findByUserId(String id);

    @Query(value = "SELECT count(u.email) FROM User u WHERE u.email=?1")
    int countByUserEmail(String email);
}
