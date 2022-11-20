package com.example.everyteam.repository;

import com.example.everyteam.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long> {
    @Query("SELECT m FROM Role m WHERE m.code=?1")
    List<Role> findAllByCode(String roleCode);
}
