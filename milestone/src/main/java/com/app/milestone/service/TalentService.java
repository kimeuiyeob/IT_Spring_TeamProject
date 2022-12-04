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
    //교육클릭시 정렬
    public List<TalentDTO> educationList() {
        return talentRepository.educationList();
    }

}
