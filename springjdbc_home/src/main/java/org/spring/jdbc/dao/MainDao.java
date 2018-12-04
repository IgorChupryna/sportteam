package org.spring.jdbc.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface MainDao<T> {

    T get(int id);

    void add(T t);

    default void add(Collection<T> t){
        t.forEach(this::add);
    }

    void set(T t);

    void del(T t);

    void del(int id);

    List<T> findAll();

}
