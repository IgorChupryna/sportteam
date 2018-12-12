package com.sportteam.spring.repository;

import com.sportteam.spring.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository<P>  extends CrudRepository<Person,Long>{

    List<Person> findByFirstName(String firstName);
}
