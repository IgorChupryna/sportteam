package org.spring.service;

import org.spring.interfaces.UserService;
import org.spring.models.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService<User> {
    private static final String INSERT_SQL = "INSERT INTO Users (login,name,age,salary) VALUES (?,?,?,?)";
    private static final String UPDATE_SQL = "UPDATE Users SET login=?,name=?,age=?,salary=? WHERE id=?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM Users";
    private static final String SELECT_ONE_SQL = "SELECT * FROM Users WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM Users WHERE id=?";
    
    private final JdbcTemplate jdbcTemplate;

    public UserServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    public User get(int id) {
        return jdbcTemplate.queryForObject(SELECT_ONE_SQL, BeanPropertyRowMapper.newInstance(User.class), id);
    }

    @Override
    public void add(User user) {
        jdbcTemplate.update(INSERT_SQL, user.getLogin(),user.getName(),user.getAge(),user.getSalary());
    }

    @Override
    public void add(Collection<User> users) {
        jdbcTemplate.batchUpdate(INSERT_SQL, users, users.size(), this::prepareStatement);
    }

    @Override
    public void set(User user) {
        jdbcTemplate.update(UPDATE_SQL, ps -> prepareStatementUpdate(ps, user));
    }

    @Override
    public void del(User user) {
        jdbcTemplate.update(DELETE_SQL,user.getId());
    }

    @Override
    public void del(int id) {
        jdbcTemplate.update(DELETE_SQL,id);
    }

    @Override
    public List<User> findAll() {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(SELECT_ALL_SQL);
        return rows.stream().map(row -> {
            User user = new User();
            user.setId((Integer)row.get("id"));
            user.setLogin((String) row.get("login"));
            user.setName((String) row.get("name"));
            user.setAge((Integer) row.get("age"));
            user.setSalary((Double) row.get("salary"));
            return user;
        }).collect(Collectors.toList());
    }

    private void prepareStatement(PreparedStatement ps, User user) throws SQLException {
        ps.setString(1, user.getLogin());
        ps.setString(2, user.getName());
        ps.setInt(3,user.getAge());
        ps.setDouble(4,user.getSalary());
    }
    private void prepareStatementUpdate(PreparedStatement ps, User user) throws SQLException {
        ps.setString(1, user.getLogin());
        ps.setString(2, user.getName());
        ps.setInt(3,user.getAge());
        ps.setDouble(4,user.getSalary());
        ps.setInt(5,user.getId());
    }
}
