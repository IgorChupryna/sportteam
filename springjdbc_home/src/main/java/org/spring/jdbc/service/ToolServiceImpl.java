package org.spring.jdbc.service;

import org.spring.jdbc.dao.MainDao;
import org.spring.jdbc.model.Tool;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collection;
import java.util.List;

public class ToolServiceImpl implements MainDao<Tool> {
    private final JdbcTemplate jdbcTemplate;

    public ToolServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }
    @Override
    public Tool get(int id) {
        return null;
    }

    @Override
    public void add(Tool tool) {

    }

    @Override
    public void add(Collection<Tool> t) {

    }

    @Override
    public void set(Tool tool) {

    }

    @Override
    public void del(Tool tool) {

    }

    @Override
    public void del(int id) {

    }

    @Override
    public Tool findByModel(String modelName) {
        return null;
    }

    @Override
    public List<Tool> findAll() {
        return null;
    }
}
