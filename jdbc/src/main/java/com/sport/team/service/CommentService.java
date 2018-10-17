package com.sport.team.service;

import com.sport.team.entity.Comment;
import com.sport.team.interfaces.MainService;

import java.sql.SQLException;

public class CommentService  implements MainService<Comment> {
    @Override
    public void add(Comment comment) throws SQLException {

    }

    @Override
    public void set(Comment comment) throws SQLException {

    }

    @Override
    public Comment get(int id) throws SQLException {
        return null;
    }
}
