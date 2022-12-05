package com.app.milestone.repository;

import com.app.milestone.domain.Search;
import com.app.milestone.domain.TalentDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

// customRepository쓰는이유는 만약 서비스중에서 하나를  Mybatis로 사용하고 싶으면 customRepository를 만들어서 사용한다.
public interface TalentCustomRepository {

    public List<TalentDTO> findAllByTalentAbleDate (Pageable pageable, Search search);

    //재능기부 클릭시 DONATION ID로 상세페이지 보기
    public List<TalentDTO> talentDetail(Long userId);

    //전체클릭시 전체리스트 뽑아오기
    public List<TalentDTO> allList();

    //교육클릭시 교육리스트 뽑아오기
    public List<TalentDTO> educationList();

    //운동클릭시 운동리스트 뽑아오기
    public List<TalentDTO> exerciseList();

    //음악클릭시 음악리스트 뽑아오기
    public List<TalentDTO> musicList();

    //미술클릭시 미술리스트 뽑아오기
    public List<TalentDTO> artList();

    //IT클릭시 IT리스트 뽑아오기
    public List<TalentDTO> itList();

}
