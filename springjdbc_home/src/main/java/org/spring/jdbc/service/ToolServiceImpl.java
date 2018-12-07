package org.spring.jdbc.service;

import org.spring.jdbc.dao.MainDao;
import org.spring.jdbc.model.Tool;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ToolServiceImpl implements MainDao<Tool> {
    private static final String INSERT_SQL = "INSERT INTO Tools (name) VALUES (?)";
    private static final String UPDATE_SQL = "UPDATE Tools SET name=? WHERE id=?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM Tools";
    private static final String SELECT_ONE_SQL = "SELECT * FROM Tools WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM Tools WHERE id=?";

    private final JdbcTemplate jdbcTemplate;

    public ToolServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }
    @Override
    public Tool get(int id) {
        return jdbcTemplate.queryForObject(SELECT_ONE_SQL, BeanPropertyRowMapper.newInstance(Tool.class), id);
    }

    @Override
    public void add(Tool tool) {
        jdbcTemplate.update(INSERT_SQL, tool.getName());
    }

    @Override
    public void add(Collection<Tool> tools) {
        jdbcTemplate.batchUpdate(INSERT_SQL, tools, tools.size(), this::prepareStatement);
    }

    @Override
    public void set(Tool tool) {
        jdbcTemplate.update(UPDATE_SQL, ps -> prepareStatementUpdate(ps, tool));
    }

    @Override
    public void del(Tool tool) {
        jdbcTemplate.update(DELETE_SQL,tool.getId());
    }

    @Override
    public void del(int id) {
        jdbcTemplate.update(DELETE_SQL,id);
    }

    @Override
    public List<Tool> findAll() {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(SELECT_ALL_SQL);
        return rows.stream().map(row -> {
            Tool tool = new Tool();
            tool.setName((String) row.get("name"));
            return tool;
        }).collect(Collectors.toList());
    }

    private void prepareStatement(PreparedStatement ps, Tool tool) throws SQLException {
        ps.setString(1, tool.getName());
    }

    private void prepareStatementUpdate(PreparedStatement ps, Tool tool) throws SQLException {
        ps.setString(1, tool.getName());
        ps.setInt(2,tool.getId());
    }
}
