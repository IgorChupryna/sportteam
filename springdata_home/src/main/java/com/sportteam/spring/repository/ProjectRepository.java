package com.sportteam.spring.repository;

import com.sportteam.spring.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository<P> extends JpaRepository<Project,Long> {
    List<Project> findByName(String name);
}
