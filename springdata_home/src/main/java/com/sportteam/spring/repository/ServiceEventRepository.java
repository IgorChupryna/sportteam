package com.sportteam.spring.repository;

import com.sportteam.spring.model.ServiceEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceEventRepository<P> extends JpaRepository<ServiceEvent,Long> {
    List<ServiceEvent> findByName(String name);
}
