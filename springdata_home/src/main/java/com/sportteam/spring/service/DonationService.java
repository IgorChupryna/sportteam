package com.sportteam.spring.service;

import com.sportteam.spring.model.Donation;
import com.sportteam.spring.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DonationService {
    @Autowired
    private DonationRepository<Donation> commentRepository;

    @Transactional
    public List<Donation> getAllDonations() {
        return (List<Donation>) commentRepository.findAll();
    }

    @Transactional
    public List<Donation> findByName(String name) {
        return commentRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public Donation getById(Long id) {
        return  commentRepository.findOne(id);
    }

    @Transactional
    public void deleteDonation(Long commentId) {
        commentRepository.delete(commentId);
    }

    @Transactional
    public boolean addDonation(Donation comment) {
        return commentRepository.save(comment) != null;
    }

    @Transactional
    public boolean updateDonation(Donation comment) {
        return commentRepository.save(comment) != null;
    }
}
