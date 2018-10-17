package com.sport.team.service;

import com.sport.team.entity.Donation;
import com.sport.team.interfaces.MainService;

import java.sql.SQLException;

public class DonationtService implements MainService<Donation> {
    @Override
    public void add(Donation donation) throws SQLException {

    }

    @Override
    public void set(Donation donation) throws SQLException {

    }

    @Override
    public Donation get(int id) throws SQLException {
        return null;
    }
}
