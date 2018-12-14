package com.sportteam.spring.repository;

import com.sportteam.spring.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommunityRepository<P> extends JpaRepository<Community,Long> {
    List<Community> findByName(String name);
}
