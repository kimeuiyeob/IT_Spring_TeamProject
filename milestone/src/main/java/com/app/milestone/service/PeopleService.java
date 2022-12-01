package com.app.milestone.service;

import com.app.milestone.domain.PeopleDTO;
import com.app.milestone.repository.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PeopleService {
    private final PeopleRepository peopleRepository;
    
//    개인회원 한 명의 정보
    public PeopleDTO onesInfo(Long userId){
        return peopleRepository.findInfoById(userId);
    }
}
