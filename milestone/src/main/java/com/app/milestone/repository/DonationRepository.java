package com.app.milestone.repository;

import com.app.milestone.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface DonationRepository extends JpaRepository<Donation, Long> {
}
