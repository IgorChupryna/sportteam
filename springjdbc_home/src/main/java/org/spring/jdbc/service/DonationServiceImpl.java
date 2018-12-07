package org.spring.jdbc.service;

import org.spring.jdbc.dao.MainDao;
import org.spring.jdbc.model.Donation;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DonationServiceImpl implements MainDao<Donation> {
    private static final String INSERT_SQL = "INSERT INTO Donation (dateAdded,amount) VALUES(?, ?)";
    private static final String INSERT_SQL_PROJECT = "INSERT INTO Project_Donations (projectId, donationId) VALUES(?, ?)";
    private static final String INSERT_SQL_USER = "INSERT INTO Users_Donations (userId, donationId) VALUES(?, ?)";

    private static final String UPDATE_SQL = "UPDATE Donation SET dateAdded=?,amount=? WHERE Donation.id=?";

    private static final String SELECT_ALL_SQL = "SELECT * FROM Donation";
    private static final String SELECT_ONE_SQL = "SELECT * FROM Donation WHERE Donation.id=?";

    private static final String DELETE_SQL = "DELETE FROM Donation WHERE id=?";
    private static final String DELETE_SQL_PROJECT = "DELETE FROM Project_Donations  WHERE Project_Donations.donationId=?";
    private static final String DELETE_SQL_USER = "DELETE FROM Users_Donations  WHERE Users_Donations.donationId=?";
    
    private final JdbcTemplate jdbcTemplate;

    public DonationServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }
    @Override
    public Donation get(int id) {
        return jdbcTemplate.queryForObject(SELECT_ONE_SQL, BeanPropertyRowMapper.newInstance(Donation.class), id);
    }

    @Override
    public void add(Donation donation) {
        jdbcTemplate.update(INSERT_SQL, donation.getDateAdded(), donation.getAmount());
        jdbcTemplate.update(INSERT_SQL_PROJECT, donation.getProject().getId(), donation.getId());
        jdbcTemplate.update(INSERT_SQL_USER, donation.getUser().getId(), donation.getId());
    }

    @Override
    public void add(Collection<Donation> donations) {
        jdbcTemplate.batchUpdate(INSERT_SQL, donations, donations.size(), this::prepareStatement);
        jdbcTemplate.batchUpdate(INSERT_SQL_PROJECT, donations, donations.size(), this::prepareStatementProjects);
        jdbcTemplate.batchUpdate(INSERT_SQL_USER, donations, donations.size(), this::prepareStatementUser);
    }

    @Override
    public void set(Donation donation) {
        jdbcTemplate.update(UPDATE_SQL, ps -> prepareStatementUpdate(ps, donation));
        jdbcTemplate.update(DELETE_SQL_PROJECT,donation.getId());
        jdbcTemplate.update(DELETE_SQL_USER,donation.getId());
        jdbcTemplate.update(INSERT_SQL_PROJECT, donation.getProject().getId(), donation.getId());
        jdbcTemplate.update(INSERT_SQL_USER, donation.getUser().getId(), donation.getId());
    }

    @Override
    public void del(Donation donation) {
        jdbcTemplate.update(DELETE_SQL,donation.getId());
        jdbcTemplate.update(DELETE_SQL_PROJECT,donation.getId());
        jdbcTemplate.update(DELETE_SQL_USER,donation.getId());
    }

    @Override
    public void del(int id) {
        jdbcTemplate.update(DELETE_SQL,id);
        jdbcTemplate.update(DELETE_SQL_PROJECT,id);
        jdbcTemplate.update(DELETE_SQL_USER,id);
    }


    @Override
    public List<Donation> findAll() {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(SELECT_ALL_SQL);
        return rows.stream().map(row -> {
            Donation donation = new Donation();
            donation.setId((Integer)row.get("id"));
            donation.setDateAdded((Date) row.get("dateAdded"));
            donation.setAmount((Double) row.get("amount"));
            return donation;
        }).collect(Collectors.toList());
    }

    private void prepareStatement(PreparedStatement ps, Donation donation) throws SQLException {
        ps.setDate(1, donation.getDateAdded());
        ps.setDouble(2, donation.getAmount());
    }

    private void prepareStatementUpdate(PreparedStatement ps, Donation donation) throws SQLException {
        ps.setDate(1, donation.getDateAdded());
        ps.setDouble(2, donation.getAmount());
        ps.setInt(3,donation.getId());
    }

    private void prepareStatementProjects(PreparedStatement ps, Donation donation) throws SQLException {
        ps.setInt(1, donation.getProject().getId());
        ps.setInt(2, donation.getId());
    }

    private void prepareStatementUser(PreparedStatement ps, Donation donation) throws SQLException {
        ps.setInt(1, donation.getUser().getId());
        ps.setInt(2, donation.getId());
    }

}
