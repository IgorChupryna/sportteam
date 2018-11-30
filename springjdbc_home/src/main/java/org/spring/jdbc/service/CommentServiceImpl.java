package org.spring.jdbc.service;

import org.spring.jdbc.dao.MainDao;
import org.spring.jdbc.model.Comment;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collection;
import java.util.List;

public class CommentServiceImpl implements MainDao<Comment> {

    private final JdbcTemplate jdbcTemplate;

    public CommentServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    public Comment get(int id) {
        return null;
    }

    @Override
    public void add(Comment comment) {

    }

    @Override
    public void add(Collection<Comment> t) {

    }

    @Override
    public void set(Comment comment) {

    }

    @Override
    public void del(Comment comment) {

    }

    @Override
    public void del(int id) {

    }

    @Override
    public Comment findByModel(String modelName) {
        return null;
    }

    @Override
    public List<Comment> findAll() {
        return null;
    }
}
