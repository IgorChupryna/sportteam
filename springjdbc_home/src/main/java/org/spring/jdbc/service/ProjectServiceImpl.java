package org.spring.jdbc.service;

import org.spring.jdbc.dao.MainDao;
import org.spring.jdbc.model.Donation;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collection;
import java.util.List;

public class ProjectServiceImpl implements MainDao<Donation> {
    private final JdbcTemplate jdbcTemplate;

    public ProjectServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }
    @Override
    public Donation get(int id) {
        return null;
    }

    @Override
    public void add(Donation donation) {

    }

    @Override
    public void add(Collection<Donation> t) {

    }

    @Override
    public void set(Donation donation) {

    }

    @Override
    public void del(Donation donation) {

    }

    @Override
    public void del(int id) {

    }

    @Override
    public Donation findByModel(String modelName) {
        return null;
    }

    @Override
    public List<Donation> findAll() {
        return null;
    }
}
