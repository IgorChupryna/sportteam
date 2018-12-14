package com.sportteam.spring.repository;

import com.sportteam.spring.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRepository<P> extends JpaRepository<Skill,Long> {
    List<Skill> findByName(String name);
}
