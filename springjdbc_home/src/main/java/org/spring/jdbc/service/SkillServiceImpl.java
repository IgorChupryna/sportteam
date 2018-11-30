package org.spring.jdbc.service;

import org.spring.jdbc.dao.MainDao;
import org.spring.jdbc.model.Skill;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collection;
import java.util.List;

public class SkillServiceImpl implements MainDao<Skill> {
    private final JdbcTemplate jdbcTemplate;

    public SkillServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }
    @Override
    public Skill get(int id) {
        return null;
    }

    @Override
    public void add(Skill skill) {

    }

    @Override
    public void add(Collection<Skill> t) {

    }

    @Override
    public void set(Skill skill) {

    }

    @Override
    public void del(Skill skill) {

    }

    @Override
    public void del(int id) {

    }

    @Override
    public Skill findByModel(String modelName) {
        return null;
    }

    @Override
    public List<Skill> findAll() {
        return null;
    }
}
