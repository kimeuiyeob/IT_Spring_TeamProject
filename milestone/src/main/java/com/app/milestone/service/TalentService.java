package com.app.milestone.service;

import com.app.milestone.domain.Search;
import com.app.milestone.domain.TalentDTO;
import com.app.milestone.repository.TalentRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TalentService {

    private final TalentRepository talentRepository;
    private final JPAQueryFactory jpaQueryFactory;

    //재능기부 목록 조회
    public List<TalentDTO> talentList(Pageable pageable, Search search) {
        return talentRepository.findAllByTalentAbleDate(pageable, search);
    }

    //글작성 등록
    public void registerTalent(TalentDTO talentDTO) {
        talentRepository.save(talentDTO.toEntity());
    }


    //재능 기부 목록 상세보기
//    public List<TalentDTO> talentDetail(Long UserId) {
//        return talentRepository.talentDetail(UserId);
//    }

    //전체클릭시 전체 조회하기 (가능일자순으로)
    public List<TalentDTO> allList() {
        return talentRepository.allList();
    }

    //교육클릭시 교육 조회하기
    public List<TalentDTO> educationList() {
        return talentRepository.educationList();
    }

    //운동클릭시 운동 조회하기
    public List<TalentDTO> exerciseList() {
        return talentRepository.exerciseList();
    }

    //음악클릭시 음악 조회하기
    public List<TalentDTO> musicList() {
        return talentRepository.musicList();
    }

    //미술클릭시 미술 조회하기
    public List<TalentDTO> artList() {
        return talentRepository.artList();
    }

    //IT클릭시 IT 조회하기
    public List<TalentDTO> itList() {
        return talentRepository.itList();
    }


}
