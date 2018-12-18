package com.sportteam.spring.service;

import com.sportteam.spring.model.Community;
import com.sportteam.spring.repository.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommunityService {
    @Autowired
    private CommunityRepository communityRepository;

    @Transactional
    public List<Community> getAllCommunitys() {
        return (List<Community>) communityRepository.findAll();
    }

    @Transactional
    public List<Community> findByName(String name) {
        return communityRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public Community getById(Long id) {
        return  communityRepository.findOne(id);
    }

    @Transactional
    public void deleteCommunity(Long communityId) {
        communityRepository.delete(communityId);
    }

    @Transactional
    public boolean addCommunity(Community community) {
        return communityRepository.save(community) != null;
    }

    @Transactional
    public boolean updateCommunity(Community community) {
        return communityRepository.save(community) != null;
    }

}
