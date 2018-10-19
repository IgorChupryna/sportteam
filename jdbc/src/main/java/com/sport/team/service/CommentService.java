package com.sport.team.service;

import com.sport.team.entity.Comment;
import com.sport.team.interfaces.MainService;

import java.sql.SQLException;

public class CommentService  implements MainService<Comment> {


    /**
     * Insert comment.
     *
     * @param comment the user
     * @throws SQLException the SQL exception
     */
    @Override
    public void add(Comment comment) throws SQLException {

    }

    /**
     * Update comment.
     *
     * @param comment the user
     * @throws SQLException the SQL exception
     */
    @Override
    public void set(Comment comment) throws SQLException {

    }


    /**
     * Gets the comment.
     *
     * @param id the id
     * @return the comment
     * @throws SQLException the SQL exception
     */
    @Override
    public Comment get(int id) throws SQLException {
        return null;
    }
}
