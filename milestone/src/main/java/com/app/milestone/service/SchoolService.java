package com.app.milestone.service;

import com.app.milestone.domain.MoneyDTO;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchoolService {
    private final SchoolRepository schoolRepository;
    private final PeopleRepository peopleRepository;
    private final MoneyRepository moneyRepository;

    //    보육원 회원가입
    public ResponseEntity schoolSignUp(SchoolDTO schoolDTO) {

        Optional<School> school = schoolRepository.findByUserEmail(schoolDTO.getUserEmail());

        if (school.isEmpty()) {
            School newSchool = School.builder()
                    .userEmail(schoolDTO.getUserEmail())
                    .userPassword(schoolDTO.getUserPassword())
                    .userName(schoolDTO.getUserName())
                    .schoolName(schoolDTO.getSchoolName())
                    .schoolTeachers(schoolDTO.getSchoolTeachers())
                    .schoolKids(schoolDTO.getSchoolKids())
                    .schoolBudget(schoolDTO.getSchoolBudget())
                    .schoolAccount(schoolDTO.getSchoolAccount())
                    .userPhoneNumber(schoolDTO.getUserPhoneNumber())
                    .schoolPhoneNumber(schoolDTO.getSchoolPhoneNumber())
                    .build();

            schoolRepository.save(newSchool);

            return new ResponseEntity("success", HttpStatus.OK);
        } else {
            return new ResponseEntity("fail", HttpStatus.OK);
        }
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
        if(page==null) page=0;
        Pageable pageable = PageRequest.of(page,10);
        if (search.getSchoolAddress() == null) {
            search.setSchoolAddress(new ArrayList<>());
            search.getSchoolAddress().add(null);
        }
        if (search.getSchoolName() == null) {
            search.setSchoolName(null);
        }
        List<SchoolDTO> list = schoolRepository.findAllByCreatedDate(pageable, search);
        int start = list.size() > (int) pageable.getOffset() ? (int) pageable.getOffset() : (int) pageable.getOffset() - 10;
        int end = Math.min((start + pageable.getPageSize()), list.size());
//        log.info("===============asdf" + pageable);
//        log.info("===============asdf" + search);
//        log.info("===============asdf" + start);
//        log.info("asd===============" + end);
//        log.info("asd===============" + schoolRepository.countByCreatedDate(pageable, search));

        Page<SchoolDTO> schools = new PageImpl<>(list.subList(start, end), pageable, Integer.valueOf("" + schoolRepository.countByCreatedDate(pageable, search)));

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

    //    보육원 목록
    public List<School> schoolListManager(Pageable pageable) {
        return schoolRepository.findByCreatedDate(pageable);
    }

    //    전체회원 중 보육원 목록
    public Page<SchoolDTO> schoolOnly(Integer page,Search search) {
        if(page==null) page=0;
        Pageable pageable = PageRequest.of(page,7);
        List<SchoolDTO> list = schoolRepository.findBySchoolOnly(pageable,search);
        int start = list.size() > (int) pageable.getOffset() ? (int) pageable.getOffset() : (int) pageable.getOffset() - 10;
        int end = Math.min((start + pageable.getPageSize()), list.size());

        Page<SchoolDTO> schools = new PageImpl<>(list.subList(start, end), pageable, Integer.valueOf("" + schoolRepository.countByCreatedDate(pageable, search)));

        return schools;
    }


    /*=============================================================================*/
    /*=============================================================================*/
    /*=============================================================================*/
    /*=============================================================================*/
    /*=============================================================================*/
    //    보육원 검색 + 예산 내림차순
    public Page<SchoolDTO> schoolListBudgetSearch(Integer page, Search search) {
        if(page==null) page=0;
        Pageable pageable = PageRequest.of(page,7);
        if (search.getSchoolAddress() == null) {
            search.setSchoolAddress(new ArrayList<>());
            search.getSchoolAddress().add(null);
        }
        if (search.getSchoolName() == null) {
            search.setSchoolName(null);
        }
        List<SchoolDTO> list = schoolRepository.findByBudgetAndSearch(pageable, search);
        log.info("결과============"+pageable.getOffset());
        int start = list.size() > (int) pageable.getOffset() ? (int) pageable.getOffset() : (int) pageable.getOffset() - 10;
        int end = Math.min((start + pageable.getPageSize()), list.size());

        Page<SchoolDTO> schools = new PageImpl<>(list.subList(start, end), pageable, Integer.valueOf("" + schoolRepository.countByCreatedDate(pageable, search)));

        return schools;
    }

    //    보육원 검색 + 예산 오름차순
    public Page<SchoolDTO> schoolListBudgetSearchAsc(Integer page, Search search) {
        if(page==null) page=0;
        Pageable pageable = PageRequest.of(page,7);
        if (search.getSchoolAddress() == null) {
            search.setSchoolAddress(new ArrayList<>());
            search.getSchoolAddress().add(null);
        }
        if (search.getSchoolName() == null) {
            search.setSchoolName(null);
        }
        List<SchoolDTO> list = schoolRepository.findByBudgetAndSearchAsc(pageable, search);
        int start = list.size() > (int) pageable.getOffset() ? (int) pageable.getOffset() : (int) pageable.getOffset() - 10;
        int end = Math.min((start + pageable.getPageSize()), list.size());

        Page<SchoolDTO> schools = new PageImpl<>(list.subList(start, end), pageable, Integer.valueOf("" + schoolRepository.countByCreatedDate(pageable, search)));

        return schools;
    }
}
