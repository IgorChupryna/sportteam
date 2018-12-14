package com.sportteam.spring.service;

import com.sportteam.spring.model.Skill;
import com.sportteam.spring.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SkillService {
    @Autowired
    private SkillRepository<Skill> skillRepository;

    @Transactional
    public List<Skill> getAllSkills() {
        return (List<Skill>) skillRepository.findAll();
    }

    @Transactional
    public List<Skill> findByName(String name) {
        return skillRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public Skill getById(Long id) {
        return  skillRepository.findOne(id);
    }

    @Transactional
    public void deleteSkill(Long skillId) {
        skillRepository.delete(skillId);
    }

    @Transactional
    public boolean addSkill(Skill skill) {
        return skillRepository.save(skill) != null;
    }

    @Transactional
    public boolean updateSkill(Skill skill) {
        return skillRepository.save(skill) != null;
    }
}
