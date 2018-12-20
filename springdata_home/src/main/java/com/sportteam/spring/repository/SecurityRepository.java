package com.sportteam.spring.repository;

import com.sportteam.spring.model.Security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface SecurityRepository extends JpaRepository<Security,Long> {
    @Query("SELECT u FROM Security u where u.login = :login")
    Security findByLogin(@Param("login") String login);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Security u WHERE u.login = :login")
    boolean existsByLogin(@Param("login") String login);
}
