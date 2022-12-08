package com.app.milestone.service;

import com.app.milestone.domain.ServiceDTO;
import com.app.milestone.entity.People;
import com.app.milestone.entity.School;
import com.app.milestone.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceService {
    private final PeopleRepository peopleRepository;
    private final SchoolRepository schoolRepository;
    private final DonationRepository donationRepository;
    private final ServiceRepository serviceRepository;

    public void donationReservation (Long userId, ServiceDTO serviceDTO){
        int donationCount = 0;
        People people = peopleRepository.findById(userId).get();

        School school = schoolRepository.findById(serviceDTO.getUserId()).get();

        com.app.milestone.entity.Service service = new com.app.milestone.entity.Service(school,people,serviceDTO.getServiceVisitDate());
        serviceRepository.save(service);
        donationCount = donationRepository.countByPeopleUserId(userId);
        people.update(donationCount);
        donationCount = donationRepository.countBySchoolUserId(serviceDTO.getUserId());
        school.update(donationCount);
    }
}
