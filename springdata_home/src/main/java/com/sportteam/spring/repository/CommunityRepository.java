package com.sportteam.spring.repository;

import com.sportteam.spring.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<Community,Long> {
    List<Community> findByName(String name);
}
