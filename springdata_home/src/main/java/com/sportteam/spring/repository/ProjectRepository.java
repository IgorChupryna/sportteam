package com.sportteam.spring.repository;

import com.sportteam.spring.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    List<Project> findByTitle(String title);
}
