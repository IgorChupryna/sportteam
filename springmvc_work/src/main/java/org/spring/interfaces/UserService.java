package org.spring.interfaces;

import java.util.Collection;
import java.util.List;

public interface UserService<T> {

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
