package com.app.milestone.service;

import com.app.milestone.domain.MoneyDTO;
import com.app.milestone.domain.PeopleDTO;
import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.domain.Search;
import com.app.milestone.entity.School;
import com.app.milestone.repository.MoneyRepository;
import com.app.milestone.repository.PeopleRepository;
import com.app.milestone.repository.SchoolRepository;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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

    //    보육원 회원가입
    public Long createSchool(SchoolDTO schoolDTO) {
        School school = schoolDTO.toEntity();
        schoolRepository.save(school);
        return school.getUserId();
    }

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

    //    보육원 정보(하나)
    public SchoolDTO schoolInfo(Long userId) {
        return schoolRepository.findByUserId(userId);
    }

    //    보육원 목록(보육원 목록)(Page버전)
    public Page<SchoolDTO> schoolList(Integer page, Search search) {
        if (page == null) page = 0;
        Pageable pageable = PageRequest.of(page, 10);
        if (search.getSchoolAddress() == null) {
            search.setSchoolAddress(new ArrayList<>());
            search.getSchoolAddress().add(null);
        }
        if (search.getSchoolName() == null) {
            search.setSchoolName(null);
        }
        List<SchoolDTO> list = schoolRepository.findAllByCreatedDate(pageable, search);
//        int start = list.size() > (int) pageable.getOffset() ? (int) pageable.getOffset() : (int) pageable.getOffset() - 10;
//        int end = Math.min((start + pageable.getPageSize()), list.size());
//        log.info("===============asdf" + pageable);
//        log.info("===============asdf" + search);
//        log.info("===============asdf" + start);
//        log.info("asd===============" + end);
//        log.info("asd===============" + schoolRepository.countByCreatedDate(pageable, search));

        Page<SchoolDTO> schools = new PageImpl<>(list, pageable, Integer.valueOf("" + schoolRepository.countByCreatedDate(pageable, search)));

        return schools;
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
            String name = peopleRepository.findById(tuple.get(1, Long.class)).get().getPeopleNickname();
            moneyDTO.setMoneyCash(tuple.get(0, Long.class));
            moneyDTO.setPeopleNickName(name);
            moneyDTOS.add(moneyDTO);
        }

        return moneyDTOS;
    }


    // 관리자 페이지============================================

    public List<School> schoolListManager(Pageable pageable) {
        return schoolRepository.findByCreatedDate(pageable);
    }

    public Page<SchoolDTO> schoolListSearch(Integer page, String keyword) {
        if (page == null) page = 0;
        Pageable pageable = PageRequest.of(page, 7);
        if (keyword == null) {
            keyword = null;
        }
        List<SchoolDTO> list = schoolRepository.findSchoolSearch(pageable, keyword);
        Page<SchoolDTO> school = new PageImpl<>(list, pageable, Integer.valueOf("" + schoolRepository.countByCreatedDate2(pageable, keyword)));
        return school;
    }

    public Page<SchoolDTO> schoolListSearchAsc(Integer page, String keyword) {
        if (page == null) page = 0;
        Pageable pageable = PageRequest.of(page, 7);
        if (keyword == null) {
            keyword = null;
        }
        List<SchoolDTO> list = schoolRepository.findSchoolSearchAsc(pageable, keyword);
        Page<SchoolDTO> school = new PageImpl<>(list, pageable, Integer.valueOf("" + schoolRepository.countByCreatedDate2(pageable, keyword)));
        return school;
    }




    //    보육원 검색 + 예산 내림차순
    public Page<SchoolDTO> schoolListBudgetSearch(Integer page, String keyword) {
        if (page == null) page = 0;
        Pageable pageable = PageRequest.of(page, 7);
        if (keyword == null) {
            keyword = null;
        }
        List<SchoolDTO> list = schoolRepository.findByBudgetAndSearch(pageable, keyword);
        Page<SchoolDTO> schools = new PageImpl<>(list, pageable, Integer.valueOf("" + schoolRepository.countByCreatedDate3(pageable, keyword)));
        return schools;
    }

    //    보육원 검색 + 예산 오름차순
    public Page<SchoolDTO> schoolListBudgetSearchAsc(Integer page, String keyword) {
        if (page == null) page = 0;
        Pageable pageable = PageRequest.of(page, 7);
        if (keyword == null) {
            keyword = null;
        }
        List<SchoolDTO> list = schoolRepository.findByBudgetAndSearchAsc(pageable, keyword);
        int start = list.size() >= (int) pageable.getOffset() ? (int) pageable.getOffset() : (int) pageable.getOffset() - 10;
        int end = Math.min((start + pageable.getPageSize()), list.size());

        Page<SchoolDTO> schools = new PageImpl<>(list.subList(start, end), pageable, Integer.valueOf("" + schoolRepository.countByCreatedDate3(pageable, keyword)));

        return schools;
    }

    //    회원 삭제
    public void deleteByUserId(Long userId){
        schoolRepository.deleteById(userId);
    }
}
