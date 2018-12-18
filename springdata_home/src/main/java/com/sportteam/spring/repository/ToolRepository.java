package com.sportteam.spring.repository;

import com.sportteam.spring.model.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ToolRepository extends JpaRepository<Tool,Long> {
    List<Tool> findByName(String name);
}
