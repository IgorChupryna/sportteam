package com.sport.team.service;

import com.sport.team.entity.ServiceEvent;
import com.sport.team.interfaces.MainService;

import java.sql.SQLException;

public class ServiceEventService implements MainService<ServiceEvent> {
    @Override
    public void add(ServiceEvent serviceEvent) throws SQLException {
        
    }

    @Override
    public void set(ServiceEvent serviceEvent) throws SQLException {

    }

    @Override
    public ServiceEvent get(int id) throws SQLException {
        return null;
    }
}
