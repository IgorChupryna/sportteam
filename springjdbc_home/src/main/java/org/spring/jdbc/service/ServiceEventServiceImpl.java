package org.spring.jdbc.service;

import org.spring.jdbc.dao.MainDao;
import org.spring.jdbc.model.ServiceEvent;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collection;
import java.util.List;

public class ServiceEventServiceImpl implements MainDao<ServiceEvent> {
    private final JdbcTemplate jdbcTemplate;

    public ServiceEventServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }
    @Override
    public ServiceEvent get(int id) {
        return null;
    }

    @Override
    public void add(ServiceEvent serviceEvent) {

    }

    @Override
    public void add(Collection<ServiceEvent> t) {

    }

    @Override
    public void set(ServiceEvent serviceEvent) {

    }

    @Override
    public void del(ServiceEvent serviceEvent) {

    }

    @Override
    public void del(int id) {

    }

    @Override
    public ServiceEvent findByModel(String modelName) {
        return null;
    }

    @Override
    public List<ServiceEvent> findAll() {
        return null;
    }
}
