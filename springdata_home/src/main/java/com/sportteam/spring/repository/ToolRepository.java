package com.sportteam.spring.repository;

import com.sportteam.spring.model.Tool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToolRepository<P> extends JpaRepository<Tool,Long> {
    List<Tool> findByName(String name);
}
