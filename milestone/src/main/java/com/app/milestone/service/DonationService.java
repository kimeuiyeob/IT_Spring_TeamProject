package com.app.milestone.service;


import com.app.milestone.aspect.Alarm;
import com.app.milestone.repository.DonationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DonationService {
    private final DonationRepository donationRepository;

    public void findDonation(Long donationId) {
        donationRepository.findById(donationId);
    }

    @Alarm
    public void alarm(Object object) {
        log.info("========================");
        log.info("=========서비스쪽===========" + object);
        log.info("========================");
    }
}
