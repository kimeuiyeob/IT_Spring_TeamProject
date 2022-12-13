package com.app.milestone.service;

import com.app.milestone.domain.FileDTO;
import com.app.milestone.domain.MoneyDTO;
import com.app.milestone.domain.Ranking;
import com.app.milestone.domain.ServiceDTO;
import com.app.milestone.entity.People;
import com.app.milestone.entity.School;
import com.app.milestone.repository.*;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceService {
    private final PeopleRepository peopleRepository;
    private final SchoolRepository schoolRepository;
    private final DonationRepository donationRepository;
    private final ServiceRepository serviceRepository;
    private final FileRepository fileRepository;

//  개인 일정 조회
    public Page<ServiceDTO> peopleServiceListSearch(Integer page, String keyword) {
        if (page == null) page = 0;
        Pageable pageable = PageRequest.of(page, 3);
        if (keyword == null) {
            keyword = null;
        }
        List<ServiceDTO> list = serviceRepository.findServiceSearch(pageable, keyword);
        Page<ServiceDTO> service = new PageImpl<>(list, pageable, Integer.valueOf("" + serviceRepository.countByCreatedDate(pageable, keyword)));
        return service;
    }

    //    방문횟수 랭킹
    public List<Ranking> donationVisitRanking() {
        List<Ranking> arRanking = new ArrayList<>();
        List<Tuple> rankingInfo = serviceRepository.sortByVisitRank();
        for (Tuple tuple : rankingInfo) {
            Ranking ranking = new Ranking();
            String peopleNickname = peopleRepository.findById(tuple.get(1, Long.TYPE)).get().getPeopleNickname();
            FileDTO fileDTO = fileRepository.findProfileByUserId(tuple.get(1, Long.TYPE));
            ranking.setFile(fileDTO);
            ranking.setPeopleNickname(peopleNickname);
            ranking.setUserId(tuple.get(1, Long.TYPE));
            ranking.setRankingItem(tuple.get(0, Long.TYPE));
            arRanking.add(ranking);
        }
        return arRanking;
    }

    //    방문기부 신청
    public void donationReservation(Long userId, ServiceDTO serviceDTO) {
        int donationCount = 0;
        People people = peopleRepository.findById(userId).get();

        School school = schoolRepository.findById(serviceDTO.getUserId()).get();

        com.app.milestone.entity.Service service = new com.app.milestone.entity.Service(school, people, serviceDTO.getServiceVisitDate());
        serviceRepository.save(service);
        donationCount = donationRepository.countByPeopleUserId(userId);
        people.update(donationCount);
        donationCount = donationRepository.countBySchoolUserId(serviceDTO.getUserId());
        school.update(donationCount);
    }

    //    관리자 페이지=======================================================

    public Page<ServiceDTO> serviceListSearch(Integer page, String keyword) {
        if (page == null) page = 0;
        Pageable pageable = PageRequest.of(page, 7);
        if (keyword == null) {
            keyword = null;
        }
        List<ServiceDTO> list = serviceRepository.findServiceSearch(pageable, keyword);
        Page<ServiceDTO> service = new PageImpl<>(list, pageable, Integer.valueOf("" + serviceRepository.countByCreatedDate(pageable, keyword)));
        return service;
    }

    public Page<ServiceDTO> serviceListSearchAsc(Integer page, String keyword) {
        if (page == null) page = 0;
        Pageable pageable = PageRequest.of(page, 7);
        if (keyword == null) {
            keyword = null;
        }
        List<ServiceDTO> list = serviceRepository.findServiceSearchAsc(pageable, keyword);
        Page<ServiceDTO> service = new PageImpl<>(list, pageable, Integer.valueOf("" + serviceRepository.countByCreatedDate(pageable, keyword)));
        return service;
    }

}
