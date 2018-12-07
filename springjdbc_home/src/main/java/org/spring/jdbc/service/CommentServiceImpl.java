package org.spring.jdbc.service;

import org.spring.jdbc.dao.MainDao;
import org.spring.jdbc.model.Comment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommentServiceImpl implements MainDao<Comment> {
    private static final String INSERT_SQL = "INSERT INTO Comment (dateAdded,text) VALUES(?, ?)";
    private static final String INSERT_SQL_PROJECT = "INSERT INTO Project_Comments (projectId, commentId) VALUES(?, ?)";
    private static final String INSERT_SQL_USER = "INSERT INTO Users_Comments (userId, commentId) VALUES(?, ?)";

    private static final String UPDATE_SQL = "UPDATE Comment SET Comment.dateAdded=?,Comment.text=? WHERE Comment.id=?";

    private static final String SELECT_ALL_SQL = "SELECT * FROM Comment";
    private static final String SELECT_ONE_SQL = "SELECT * FROM Comment WHERE Comment.id=?";

    private static final String DELETE_SQL = "DELETE FROM Comment WHERE id=?";
    private static final String DELETE_SQL_PROJECT = "DELETE FROM Project_Comments  WHERE Project_Comments.commentId=?";
    private static final String DELETE_SQL_USER = "DELETE FROM Users_Comments  WHERE Users_Comments.commentId=?";

    private final JdbcTemplate jdbcTemplate;

    public CommentServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Comment get(int id) {
        return jdbcTemplate.queryForObject(SELECT_ONE_SQL, BeanPropertyRowMapper.newInstance(Comment.class), id);
    }

    @Override
    public void add(Comment comment) {
        jdbcTemplate.update(INSERT_SQL, comment.getDateAdded(), comment.getText());
        jdbcTemplate.update(INSERT_SQL_PROJECT, comment.getProject().getId(), comment.getId());
        jdbcTemplate.update(INSERT_SQL_USER, comment.getSubmitter().getId(), comment.getId());
    }

    @Override
    public void add(Collection<Comment> comments) {
        jdbcTemplate.batchUpdate(INSERT_SQL, comments, comments.size(), this::prepareStatement);
        jdbcTemplate.batchUpdate(INSERT_SQL_PROJECT, comments, comments.size(), this::prepareStatement);
        jdbcTemplate.batchUpdate(INSERT_SQL_USER, comments, comments.size(), this::prepareStatement);
    }

    @Override
    public void set(Comment comment) {
        jdbcTemplate.update(UPDATE_SQL, ps -> prepareStatementUpdate(ps, comment));
        jdbcTemplate.update(DELETE_SQL_PROJECT,comment.getId());
        jdbcTemplate.update(DELETE_SQL_USER,comment.getId());
        jdbcTemplate.update(INSERT_SQL_PROJECT, comment.getProject().getId(), comment.getId());
        jdbcTemplate.update(INSERT_SQL_USER, comment.getSubmitter().getId(), comment.getId());

    }

    @Override
    public void del(Comment comment) {
        jdbcTemplate.update(DELETE_SQL,comment.getId());
        jdbcTemplate.update(DELETE_SQL_PROJECT,comment.getId());
        jdbcTemplate.update(DELETE_SQL_USER,comment.getId());
    }

    @Override
    public void del(int id) {
        jdbcTemplate.update(DELETE_SQL,id);
        jdbcTemplate.update(DELETE_SQL_PROJECT,id);
        jdbcTemplate.update(DELETE_SQL_USER,id);
    }


    @Override
    public List<Comment> findAll() {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(SELECT_ALL_SQL);
        return rows.stream().map(row -> {
            Comment comment = new Comment();
            comment.setId((Integer)row.get("id"));
            comment.setDateAdded((Calendar) row.get("dateAdded"));
            comment.setText((String) row.get("text"));
            return comment;
        }).collect(Collectors.toList());
    }

    private void prepareStatement(PreparedStatement ps, Comment comment) throws SQLException {
        ps.setDate(1, new java.sql.Date(comment.getDateAdded().getTimeInMillis()));
        ps.setString(2, comment.getText());
    }

    private void prepareStatementUpdate(PreparedStatement ps, Comment comment) throws SQLException {
        ps.setDate(1, new java.sql.Date(comment.getDateAdded().getTimeInMillis()));
        ps.setString(2, comment.getText());
        ps.setInt(3,comment.getId());
    }

    private void prepareStatementProjects(PreparedStatement ps, Comment comment) throws SQLException {
        ps.setInt(1, comment.getProject().getId());
        ps.setInt(2, comment.getId());
    }

    private void prepareStatementSubmitter(PreparedStatement ps, Comment comment) throws SQLException {
        ps.setInt(1, comment.getSubmitter().getId());
        ps.setInt(2, comment.getId());
    }

}
