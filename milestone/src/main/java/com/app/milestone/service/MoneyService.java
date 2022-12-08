package com.app.milestone.service;

import com.app.milestone.domain.MoneyDTO;
import com.app.milestone.entity.Donation;
import com.app.milestone.entity.Money;
import com.app.milestone.entity.People;
import com.app.milestone.entity.School;
import com.app.milestone.repository.DonationRepository;
import com.app.milestone.repository.MoneyRepository;
import com.app.milestone.repository.PeopleRepository;
import com.app.milestone.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MoneyService {
    private final PeopleRepository peopleRepository;
    private final SchoolRepository schoolRepository;
    private final DonationRepository donationRepository;
    private final MoneyRepository moneyRepository;
    
//    결제
    @Transactional
    public void payment(Long userId, MoneyDTO moneyDTO){
        int donationCount = 0;
        People people = peopleRepository.findById(userId).get();

        School school = schoolRepository.findById(moneyDTO.getUserId()).get();

        Money money = new Money(school, people, moneyDTO.getMoneyCash());
        moneyRepository.save(money);
        donationCount = donationRepository.countByPeopleUserId(userId);
        people.update(donationCount);
        donationCount = donationRepository.countBySchoolUserId(moneyDTO.getUserId());
        school.update(donationCount);
    }

}
