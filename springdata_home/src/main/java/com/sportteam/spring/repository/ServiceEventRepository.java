package com.sportteam.spring.repository;

import com.sportteam.spring.model.ServiceEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceEventRepository extends JpaRepository<ServiceEvent,Long> {
    List<ServiceEvent> findByName(String name);
}
