package com.app.milestone.repository;

import com.app.milestone.domain.PeopleDTO;
import com.app.milestone.domain.Search;
import com.querydsl.core.Tuple;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PeopleCustomRepository {
    public PeopleDTO findInfoById(Long userId);
//    public List<Tuple> sortByMoneyCash();
//    public List<Tuple> sortByVisitRank();

    public Long countByCreatedDate (Pageable pageable, String keyword);
    public List<PeopleDTO> findPeopleSearch(Pageable pageable,String keyword);
    public List<PeopleDTO> findPeopleSearchAsc(Pageable pageable,String keyword);
}
