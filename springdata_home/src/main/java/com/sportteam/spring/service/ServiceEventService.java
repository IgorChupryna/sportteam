package com.sportteam.spring.service;

import com.sportteam.spring.model.ServiceEvent;
import com.sportteam.spring.repository.ServiceEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceEventService {
    @Autowired
    private ServiceEventRepository serviceEventRepository;

    @Transactional
    public List<ServiceEvent> getAllServiceEvents() {
        return (List<ServiceEvent>) serviceEventRepository.findAll();
    }

    @Transactional
    public List<ServiceEvent> findByName(String name) {
        return serviceEventRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public ServiceEvent getById(Long id) {
        return  serviceEventRepository.findOne(id);
    }

    @Transactional
    public void deleteServiceEvent(Long serviceEventId) {
        serviceEventRepository.delete(serviceEventId);
    }

    @Transactional
    public boolean addServiceEvent(ServiceEvent serviceEvent) {
        return serviceEventRepository.save(serviceEvent) != null;
    }

    @Transactional
    public boolean updateServiceEvent(ServiceEvent serviceEvent) {
        return serviceEventRepository.save(serviceEvent) != null;
    }
}
