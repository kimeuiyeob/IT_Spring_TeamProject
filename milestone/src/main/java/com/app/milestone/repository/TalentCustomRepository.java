package com.app.milestone.repository;

import com.app.milestone.domain.Search;
import com.app.milestone.domain.TalentDTO;
import com.querydsl.core.Tuple;
import org.springframework.data.domain.Pageable;

import java.util.List;

// customRepository쓰는이유는 만약 서비스중에서 하나를  Mybatis로 사용하고 싶으면 customRepository를 만들어서 사용한다.

public interface TalentCustomRepository {
//    재능기부 랭킹
    public List<Tuple> sortBytalentRank();

    //페이징 및 검색
    public List<TalentDTO> findAllByTalentAbleDate (Pageable pageable, Search search);

    public Long countByAbleDate (Pageable pageable, Search search);

    //재능기부 클릭시 DONATION ID로 상세페이지 보기
    public List<TalentDTO> talentDetail(Long donationId);


    public List<TalentDTO> findTalentSearch (Pageable pageable, Search search);

    public Long countByCreatedDate(Pageable pageable, Search search);

    //마이페이지 나의 재능기부 -> 재능 기부 목록 가져오기
    public List<TalentDTO> findAllTalentById(Pageable pageable, Long peopleId);

    public Long countByAbleDate2 (Pageable pageable, Long peopleId);

    //재능기부 목록 페이징
    public Long countByAbleDate3 (Pageable pageable, Search search);

    //도네이션 아이디로 해당 DTO 조회
    public List<TalentDTO> findByDonationId (Long donationId);


}
