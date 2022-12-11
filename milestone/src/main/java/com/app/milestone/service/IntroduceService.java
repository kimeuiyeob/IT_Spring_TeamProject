package com.app.milestone.service;

import com.app.milestone.repository.DonationRepository;
import com.app.milestone.repository.PeopleRepository;
import com.app.milestone.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class IntroduceService {

    private final PeopleRepository peopleRepository;
    private final SchoolRepository schoolRepository;
    private final DonationRepository donationRepository;

    //개인회원 갯수 세기
    public long PeopleCount() {
       var PeopleCount  = peopleRepository.count();
       return PeopleCount;
    }

    //보육원 갯수 세기
    public long SchoolCount() {
        var SchoolCount  = schoolRepository.count();
        return SchoolCount;
    }

    public long DonationCount() {
        var DonationCount = donationRepository.count();
        return DonationCount;
    }



}
