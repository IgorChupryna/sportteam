package org.spring.jdbc.service;

import org.spring.jdbc.dao.MainDao;
import org.spring.jdbc.model.Community;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommunityServiceImpl implements MainDao<Community> {
    private static final String INSERT_SQL = "INSERT INTO Community (NAME) VALUES(?)";
    private static final String INSERT_SQL_USER = "INSERT INTO Users_Community_Created (userId, communityId) VALUES(?, ?)";

    private static final String UPDATE_SQL = "UPDATE Community SET name=? WHERE Community.id=?";

    private static final String SELECT_ALL_SQL = "SELECT * FROM Community";
    private static final String SELECT_ONE_SQL = "SELECT * FROM Community WHERE Community.id=?";

    private static final String DELETE_SQL = "DELETE FROM Community WHERE id=?";

    private static final String DELETE_SQL_USER = "DELETE FROM Users_Community_Created  WHERE Users_Community_Created.communityId=?";
    private final JdbcTemplate jdbcTemplate;

    public CommunityServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    public Community get(int id) {
        return jdbcTemplate.queryForObject(SELECT_ONE_SQL, BeanPropertyRowMapper.newInstance(Community.class), id);
    }

    @Override
    public void add(Community community) {
        jdbcTemplate.update(INSERT_SQL, community.getName());
        jdbcTemplate.update(INSERT_SQL_USER, community.getCreator().getId(), community.getId());
    }

    @Override
    public void add(Collection<Community> communitys) {
        jdbcTemplate.batchUpdate(INSERT_SQL, communitys, communitys.size(), this::prepareStatement);
        jdbcTemplate.batchUpdate(INSERT_SQL_USER, communitys, communitys.size(), this::prepareStatement);
    }

    @Override
    public void set(Community community) {
        jdbcTemplate.update(UPDATE_SQL, ps -> prepareStatement(ps, community));
        jdbcTemplate.update(DELETE_SQL_USER,community.getId());
        jdbcTemplate.update(INSERT_SQL_USER, community.getCreator().getId(), community.getId());
    }

    @Override
    public void del(Community community) {
        jdbcTemplate.update(DELETE_SQL,community.getId());
        jdbcTemplate.update(DELETE_SQL_USER,community.getId());
    }

    @Override
    public void del(int id) {
        jdbcTemplate.update(DELETE_SQL,id);
        jdbcTemplate.update(DELETE_SQL_USER,id);
    }


    @Override
    public List<Community> findAll() {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(SELECT_ALL_SQL);
        return rows.stream().map(row -> {
            Community community = new Community();
            community.setId((Integer)row.get("id"));
            community.setName((String) row.get("name"));
            return community;
        }).collect(Collectors.toList());
    }

    private void prepareStatement(PreparedStatement ps, Community community) throws SQLException {
        ps.setString(1, community.getName());
    }



    private void prepareStatementCreator(PreparedStatement ps, Community community) throws SQLException {
        ps.setInt(1, community.getCreator().getId());
        ps.setInt(2, community.getId());
    }
}
