package com.sport.team.interfaces;

import java.sql.SQLException;

public interface MainService<T> {
    public void add(T t) throws SQLException;
    public void set(T t) throws SQLException;
    public T get(int id) throws SQLException;
    public void del(T t) throws SQLException;
    public void del(int id) throws SQLException;

}
