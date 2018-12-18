package com.sportteam.spring.service;

import com.sportteam.spring.model.Security;
import com.sportteam.spring.repository.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SecurityService {
    @Autowired
    private SecurityRepository securityRepository;

    @Transactional
    public List<Security> getAllSecuritys() {
        return (List<Security>) securityRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Security getUserByLogin(String login) {
        return securityRepository.findByLogin(login);
    }


    @Transactional(readOnly = true)
    public boolean existsByLogin(String login) {
        return securityRepository.existsByLogin(login);
    }

    @Transactional(readOnly = true)
    public Security getById(Long id) {
        return  securityRepository.findOne(id);
    }

    @Transactional
    public void deleteSecurity(Long securityId) {
        securityRepository.delete(securityId);
    }

    @Transactional
    public boolean addSecurity(Security security) {
        return securityRepository.save(security) != null;
    }

    @Transactional
    public boolean updateSecurity(Security security) {
        return securityRepository.save(security) != null;
    }
}
