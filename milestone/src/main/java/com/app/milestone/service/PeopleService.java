package com.app.milestone.service;

import com.app.milestone.domain.PeopleDTO;
import com.app.milestone.domain.Ranking;
import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.domain.Search;
import com.app.milestone.entity.Like;
import com.app.milestone.entity.People;
import com.app.milestone.entity.School;
import com.app.milestone.repository.LikeRepository;
import com.app.milestone.repository.PeopleRepository;
import com.app.milestone.repository.SchoolRepository;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
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
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final SchoolRepository schoolRepository;
    private final LikeRepository likeRepository;

    public ResponseEntity peopleSignUp(PeopleDTO peopleDTO) {

        Optional<People> people = peopleRepository.findByUserEmail(peopleDTO.getUserEmail());

        if (people.isEmpty()) {
            People newPeople = People.builder()
                    .userEmail(peopleDTO.getUserEmail())
                    .userPassword(peopleDTO.getUserPassword())
                    .userName(peopleDTO.getUserName())
                    .peopleNickname(peopleDTO.getPeopleNickname())
                    .userPhoneNumber(peopleDTO.getUserPhoneNumber())
                    .build();

            peopleRepository.save(newPeople);

            return new ResponseEntity("success", HttpStatus.OK);
        } else {
            return new ResponseEntity("fail", HttpStatus.OK);
        }
    }

    //    개인회원 한 명의 정보
    public PeopleDTO onesInfo(Long userId) {
        return peopleRepository.findInfoById(userId);
    }

    //    ============================좋아요=========================
    //    내가 누른 좋아요
    public List<Long> likeSchoolList(Long sessionId) {
        List<Long> arUserId = new ArrayList<>();
        List<Like> likes = likeRepository.findByPeopleUserId(sessionId);
        for (Like like : likes) {
            arUserId.add(like.getSchool().getUserId());
        }
        return arUserId;
    }

    //    좋아요 누름
    public Long likeSchool(Long userId, Long sessionId) {
        School school = schoolRepository.findById(userId).get();
        People people = peopleRepository.findById(sessionId).get();
        Like like = new Like(school, people);
        return likeRepository.save(like).getLikeId();
    }

    //    좋아요 개수
    public Long likeCount(Long userId) {
        return likeRepository.countBySchoolUserId(userId);
    }

    //    ===================================기부 랭킹====================================
    //    기부금 랭킹
    public List<Ranking> donationMoneyRanking() {
        List<Ranking> arRanking = new ArrayList<>();
        List<Tuple> rankingInfo = peopleRepository.sortByMoneyCash();
        for (Tuple tuple : rankingInfo) {
            Ranking ranking = new Ranking();
            String userName = peopleRepository.findById(tuple.get(1, Long.TYPE)).get().getUserName();
            ranking.setUserName(userName);
            ranking.setUserId(tuple.get(1, Long.TYPE));
            ranking.setRankingItem(tuple.get(0, Long.TYPE));
            arRanking.add(ranking);
        }
        return arRanking;
    }

    //    방문횟수 랭킹
    public List<Ranking> donationVisitRanking() {
        List<Ranking> arRanking = new ArrayList<>();
        List<Tuple> rankingInfo = peopleRepository.sortByVisitRank();
        for (Tuple tuple : rankingInfo) {
            Ranking ranking = new Ranking();
            String userName = peopleRepository.findById(tuple.get(1, Long.TYPE)).get().getUserName();
            ranking.setUserName(userName);
            ranking.setUserId(tuple.get(1, Long.TYPE));
            ranking.setRankingItem(tuple.get(0, Long.TYPE));
            arRanking.add(ranking);
        }
        return arRanking;
    }

    //    재능기부 횟수 랭킹
    public List<Ranking> donationTalentRanking() {
        List<Ranking> arRanking = new ArrayList<>();
        List<Tuple> rankingInfo = peopleRepository.sortBytalentRank();
        for (Tuple tuple : rankingInfo) {
            Ranking ranking = new Ranking();
            String userName = peopleRepository.findById(tuple.get(1, Long.TYPE)).get().getUserName();
            ranking.setUserName(userName);
            ranking.setUserId(tuple.get(1, Long.TYPE));
            ranking.setRankingItem(tuple.get(0, Long.TYPE));
            arRanking.add(ranking);
        }
        return arRanking;
    }


    /* ==============관리자페이지================================================= */
    //    회원 수
    public Long peopleListCount(Pageable pageable, Search search) {
        return peopleRepository.countByCreatedDate(pageable, search);
    }

    public Page<PeopleDTO> peopleListSearch(Integer page, Search search) {
        if (page == null) page = 0;
        Pageable pageable = PageRequest.of(page, 7);

        if (search.getUserName() == null) {
            search.setUserName(null);
        }
        if (search.getPeopleNickName() == null) {
            search.setPeopleNickName(null);
        }
        List<PeopleDTO> list = peopleRepository.findPeopleSearch(pageable, search);
        int start = list.size() > (int) pageable.getOffset() ? (int) pageable.getOffset() : (int) pageable.getOffset() - 10;
        int end = Math.min((start + pageable.getPageSize()), list.size());

        Page<PeopleDTO> people = new PageImpl<>(list.subList(start, end), pageable, Integer.valueOf("" + peopleRepository.countByCreatedDate(pageable, search)));

        return people;
    }

    public Page<PeopleDTO> peopleListSearchAsc(Integer page, Search search) {
        if (page == null) page = 0;
        Pageable pageable = PageRequest.of(page, 7);

        if (search.getUserName() == null) {
            search.setUserName(null);
        }
        if (search.getPeopleNickName() == null) {
            search.setPeopleNickName(null);
        }
        List<PeopleDTO> list = peopleRepository.findPeopleSearchAsc(pageable, search);
        int start = list.size() > (int) pageable.getOffset() ? (int) pageable.getOffset() : (int) pageable.getOffset() - 10;
        int end = Math.min((start + pageable.getPageSize()), list.size());

        Page<PeopleDTO> people = new PageImpl<>(list.subList(start, end), pageable, Integer.valueOf("" + peopleRepository.countByCreatedDate(pageable, search)));

        return people;
    }


}
