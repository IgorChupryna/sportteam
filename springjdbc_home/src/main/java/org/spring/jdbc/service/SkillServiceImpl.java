package org.spring.jdbc.service;

import org.spring.jdbc.dao.MainDao;
import org.spring.jdbc.model.Skill;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SkillServiceImpl implements MainDao<Skill> {

    private static final String INSERT_SQL = "INSERT INTO Skills (name) VALUES (?)";
    private static final String UPDATE_SQL = "UPDATE Skills SET name=? WHERE id=?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM Skills";
    private static final String SELECT_ONE_SQL = "SELECT * FROM Skills WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM Skills WHERE id=?";

    private final JdbcTemplate jdbcTemplate;

    public SkillServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }
    @Override
    public Skill get(int id) {
        return jdbcTemplate.queryForObject(SELECT_ONE_SQL, BeanPropertyRowMapper.newInstance(Skill.class), id);
    }

    @Override
    public void add(Skill skill) {
        jdbcTemplate.update(INSERT_SQL, skill.getName());
    }

    @Override
    public void add(Collection<Skill> skills) {
        jdbcTemplate.batchUpdate(INSERT_SQL, skills, skills.size(), this::prepareStatement);
    }

    @Override
    public void set(Skill skill) {
        jdbcTemplate.update(UPDATE_SQL, ps -> prepareStatementUpdate(ps, skill));
    }

    @Override
    public void del(Skill skill) {
        jdbcTemplate.update(DELETE_SQL,skill.getId());
    }

    @Override
    public void del(int id) {
        jdbcTemplate.update(DELETE_SQL,id);
    }

    @Override
    public List<Skill> findAll() {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(SELECT_ALL_SQL);
        return rows.stream().map(row -> {
            Skill skill = new Skill();
            skill.setId((Integer)row.get("id"));
            skill.setName((String) row.get("name"));
            return skill;
        }).collect(Collectors.toList());
    }

    private void prepareStatement(PreparedStatement ps, Skill skill) throws SQLException {
        ps.setString(1, skill.getName());
    }
    private void prepareStatementUpdate(PreparedStatement ps, Skill skill) throws SQLException {
        ps.setString(1, skill.getName());
        ps.setInt(2,skill.getId());
    }
}
