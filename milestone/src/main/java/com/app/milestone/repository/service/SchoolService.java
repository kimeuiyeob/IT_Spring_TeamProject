package com.app.milestone.repository.service;

import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.domain.Search;
import com.app.milestone.entity.Money;
import com.app.milestone.entity.School;
import com.app.milestone.repository.MoneyRepository;
import com.app.milestone.repository.SchoolRepository;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;
    private final MoneyRepository moneyRepository;

    //    보육원 등록
    public void registerSchool(SchoolDTO schoolDTO) {
        schoolRepository.save(schoolDTO.toEntity());
    }

    //    도움이 필요한 보육원(메인)
    public List<School> needHelpList() {
        return schoolRepository.findAllByDonationCount();
    }

    //    보육원 목록(보육원 목록)
    public List<School> schoolList(Pageable pageable, Search search) {
        return schoolRepository.findAllByCreatedDate(pageable, search);
    }

    //    총 보육원 수
    public Long countSchool() {
        return schoolRepository.countBy();
    }

    //    보육원 하나에 대한 최근 기부받은 내역
    public List<Money> recentDonationList() {
        return moneyRepository.findByOrderByCreatedDateDesc();
    }

    //    보육원 하나에 대한 기부금 랭킹
    public List<Tuple> moneyDonationRankingForOneSchool(Long userId) {
        return moneyRepository.moneyRankingByOne(userId);
    }

}
