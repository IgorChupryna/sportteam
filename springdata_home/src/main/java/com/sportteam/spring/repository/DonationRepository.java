package com.sportteam.spring.repository;

import com.sportteam.spring.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRepository<P> extends JpaRepository<Donation,Long> {
    List<Donation> findByName(String name);
}
