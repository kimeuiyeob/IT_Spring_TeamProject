package com.app.milestone.repository;

import com.app.milestone.domain.Search;
import com.app.milestone.domain.TalentDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

// customRepository쓰는이유는 만약 서비스중에서 하나를  Mybatis로 사용하고 싶으면 customRepository를 만들어서 사용한다.
public interface TalentCustomRepository {

    public List<TalentDTO> findAllByTalentAbleDate (Pageable pageable, Search search);
    public List<TalentDTO> talentDetail(Long userId);
    public List<TalentDTO> educationList();

}
