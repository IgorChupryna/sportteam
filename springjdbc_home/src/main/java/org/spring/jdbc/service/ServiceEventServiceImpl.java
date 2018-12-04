package org.spring.jdbc.service;

import org.spring.jdbc.dao.MainDao;
import org.spring.jdbc.model.Project;
import org.spring.jdbc.model.ServiceEvent;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ServiceEventServiceImpl implements MainDao<ServiceEvent> {
    private static final String INSERT_SQL = "INSERT INTO Service_EO (name,description,date) VALUES(?, ?,?)";
    private static final String INSERT_SQL_PROJECT = "INSERT INTO Project_ServiceEvent (projectId, serviceEventId) VALUES(?, ?)";
    private static final String INSERT_SQL_USER = "INSERT INTO User_Service_EO (userId, serviceEOId) VALUES(?, ?)";
    private static final String INSERT_SQL_COMMUNITY = "INSERT INTO Service_EO_community (serviceEOId, communityId) VALUES(?, ?)";

    private static final String UPDATE_SQL = "UPDATE Service_EO SET name=?,description=?,date=? WHERE Service_EO.id=?";

    private static final String SELECT_ALL_SQL = "SELECT * FROM Service_EO";
    private static final String SELECT_ONE_SQL = "SELECT * FROM Service_EO WHERE Service_EO.id=?";

    private static final String DELETE_SQL = "DELETE FROM Service_EO WHERE id=?";
    private static final String DELETE_SQL_PROJECT = "DELETE FROM Project_serviceEvent  WHERE Project_serviceEvent.serviceEventId=?";
    private static final String DELETE_SQL_USER = "DELETE FROM User_Service_EO  WHERE User_Service_EO.serviceEOId=?";
    private static final String DELETE_SQL_COMMUNITY = "DELETE FROM Service_EO_community  WHERE Users_ServiceEvents.serviceEventId=?";

    private final JdbcTemplate jdbcTemplate;

    public ServiceEventServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }
    @Override
    public ServiceEvent get(int id) {
        return jdbcTemplate.queryForObject(SELECT_ONE_SQL, BeanPropertyRowMapper.newInstance(ServiceEvent.class), id);
    }

    @Override
    public void add(ServiceEvent serviceEvent) {
        jdbcTemplate.update(INSERT_SQL, serviceEvent.getName(), serviceEvent.getDescription(),serviceEvent.getDate());
        for (Project p:serviceEvent.getProjects()) {
            jdbcTemplate.update(INSERT_SQL_PROJECT, p.getId(), serviceEvent.getId());
        }
        jdbcTemplate.update(INSERT_SQL_USER, serviceEvent.getOrganizer().getId(), serviceEvent.getId());
        jdbcTemplate.update(INSERT_SQL_COMMUNITY, serviceEvent.getCommunity().getId(), serviceEvent.getId());
    }

    @Override
    public void add(Collection<ServiceEvent> serviceEvents) {
        jdbcTemplate.batchUpdate(INSERT_SQL, serviceEvents, serviceEvents.size(), this::prepareStatement);
        for (ServiceEvent serviceEvent:serviceEvents) {
            for (Project p:serviceEvent.getProjects()) {
                jdbcTemplate.update(INSERT_SQL_PROJECT, p.getId(), serviceEvent.getId());
            }
        }
        jdbcTemplate.batchUpdate(INSERT_SQL_USER, serviceEvents, serviceEvents.size(), this::prepareStatementUser);
        jdbcTemplate.batchUpdate(INSERT_SQL_COMMUNITY, serviceEvents, serviceEvents.size(), this::prepareStatementCommunity);
    }

    @Override
    public void set(ServiceEvent serviceEvent) {
        jdbcTemplate.update(UPDATE_SQL, ps -> prepareStatement(ps, serviceEvent));

        jdbcTemplate.update(DELETE_SQL_PROJECT,serviceEvent.getId());
        jdbcTemplate.update(DELETE_SQL_USER,serviceEvent.getId());
        jdbcTemplate.update(DELETE_SQL_COMMUNITY,serviceEvent.getId());

        for (Project p:serviceEvent.getProjects()) {
            jdbcTemplate.update(INSERT_SQL_PROJECT, p.getId(), serviceEvent.getId());
        }
        jdbcTemplate.update(INSERT_SQL_USER, serviceEvent.getOrganizer().getId(), serviceEvent.getId());
        jdbcTemplate.update(INSERT_SQL_COMMUNITY, serviceEvent.getCommunity().getId(), serviceEvent.getId());

    }

    @Override
    public void del(ServiceEvent serviceEvent) {
        jdbcTemplate.update(DELETE_SQL,serviceEvent.getId());
        jdbcTemplate.update(DELETE_SQL_PROJECT,serviceEvent.getId());
        jdbcTemplate.update(DELETE_SQL_USER,serviceEvent.getId());
        jdbcTemplate.update(DELETE_SQL_COMMUNITY,serviceEvent.getId());
    }

    @Override
    public void del(int id) {
        jdbcTemplate.update(DELETE_SQL,id);
        jdbcTemplate.update(DELETE_SQL_PROJECT,id);
        jdbcTemplate.update(DELETE_SQL_USER,id);
    }


    @Override
    public List<ServiceEvent> findAll() {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(SELECT_ALL_SQL);
        return rows.stream().map(row -> {
            ServiceEvent serviceEvent = new ServiceEvent();
            serviceEvent.setId((Integer)row.get("id"));
            serviceEvent.setName((String) row.get("name"));
            serviceEvent.setDescription((String) row.get("description"));
            serviceEvent.setDate((Calendar) row.get("date"));
            return serviceEvent;
        }).collect(Collectors.toList());
    }

    private void prepareStatement(PreparedStatement ps, ServiceEvent serviceEvent) throws SQLException {
        ps.setString(1, serviceEvent.getName());
        ps.setString(2, serviceEvent.getDescription());
        ps.setDate(3, new java.sql.Date(serviceEvent.getDate().getTimeInMillis()));
    }


    private void prepareStatementUser(PreparedStatement ps, ServiceEvent serviceEvent) throws SQLException {
        ps.setInt(1, serviceEvent.getOrganizer().getId());
        ps.setInt(2, serviceEvent.getId());
    }

    private void prepareStatementCommunity(PreparedStatement ps, ServiceEvent serviceEvent) throws SQLException {
        ps.setInt(1, serviceEvent.getCommunity().getId());
        ps.setInt(2, serviceEvent.getId());
    }

}
