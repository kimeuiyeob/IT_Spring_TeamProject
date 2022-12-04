package com.app.milestone.service;

import com.app.milestone.domain.MoneyDTO;
import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.domain.Search;
import com.app.milestone.entity.Money;
import com.app.milestone.entity.School;
import com.app.milestone.repository.MoneyRepository;
import com.app.milestone.repository.PeopleRepository;
import com.app.milestone.repository.SchoolRepository;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchoolService {
    private final SchoolRepository schoolRepository;
    private final PeopleRepository peopleRepository;
    private final MoneyRepository moneyRepository;

    //    보육원 등록
    public void registerSchool(SchoolDTO schoolDTO) {
        schoolRepository.save(schoolDTO.toEntity());
    }

    //    도움이 필요한 보육원(메인)
    public List<SchoolDTO> needHelpList() {
        List<SchoolDTO> arSchoolDTO = schoolRepository.findAllByDonationCount();
        for (SchoolDTO schoolDTO : arSchoolDTO) {
            schoolDTO.setSchoolAddress(schoolDTO.getSchoolAddress().split(schoolDTO.getSchoolAddress().charAt(2) + "")[0]);
        }
        return arSchoolDTO;
    }

    //    보육원 목록(보육원 목록)
    public List<SchoolDTO> schoolList(Pageable pageable, Search search) {
        return schoolRepository.findAllByCreatedDate(pageable, search);
    }

    //    보육원 목록(보육원 목록) 수
    public Long schoolListCount(Pageable pageable, Search search) {
        return schoolRepository.countByCreatedDate(pageable, search);
    }

    //    총 보육원 수
    public Long schoolTotal() {
        return schoolRepository.countBy();
    }

    //    보육원 하나에 대한 최근 기부받은 내역
    public List<MoneyDTO> recentDonationList(Long userId) {
        return moneyRepository.findByCreateDateByUserId(userId);
    }

    //    보육원 하나에 대한 기부금 랭킹
    public List<MoneyDTO> moneyDonationRankingForOneSchool(Long userId) {
        List<MoneyDTO> moneyDTOS = new ArrayList<>();
        List<Tuple> tuples = moneyRepository.moneyRankingByOne(userId);
        for (Tuple tuple : tuples) {
            MoneyDTO moneyDTO = new MoneyDTO();
            String name = peopleRepository.findById(tuple.get(1, Long.class)).get().getUserName();
            moneyDTO.setMoneyCash(tuple.get(0, Long.class));
            moneyDTO.setUserName(name);
            moneyDTOS.add(moneyDTO);
        }

        return moneyDTOS;
    }

}
