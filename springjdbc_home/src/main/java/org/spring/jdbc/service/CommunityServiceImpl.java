package org.spring.jdbc.service;

import org.spring.jdbc.dao.MainDao;
import org.spring.jdbc.model.Community;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collection;
import java.util.List;

public class CommunityServiceImpl implements MainDao<Community> {
    private final JdbcTemplate jdbcTemplate;

    public CommunityServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    public Community get(int id) {
        return null;
    }


    @Override
    public void add(Community community) {

    }

    @Override
    public void add(Collection<Community> t) {

    }


    @Override
    public void set(Community community) {

    }

    @Override
    public void del(Community community) {

    }

    @Override
    public void del(int id) {

    }

    @Override
    public Community findByModel(String modelName) {
        return null;
    }

    @Override
    public List<Community> findAll() {
        return null;
    }
}
