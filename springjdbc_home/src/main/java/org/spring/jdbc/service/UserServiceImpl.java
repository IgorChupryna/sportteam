package org.spring.jdbc.service;

import org.spring.jdbc.dao.MainDao;
import org.spring.jdbc.model.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collection;
import java.util.List;

public class UserServiceImpl implements MainDao<User> {
    private final JdbcTemplate jdbcTemplate;

    public UserServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }
    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public void add(User user) {

    }

    @Override
    public void add(Collection<User> t) {

    }

    @Override
    public void set(User user) {

    }

    @Override
    public void del(User user) {

    }

    @Override
    public void del(int id) {

    }

    @Override
    public User findByModel(String modelName) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
