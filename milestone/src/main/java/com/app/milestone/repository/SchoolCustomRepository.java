package com.app.milestone.repository;

import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.domain.Search;
import com.app.milestone.entity.School;
import com.querydsl.core.Tuple;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SchoolCustomRepository {
    public List<SchoolDTO> findAllByDonationCount ();
    public List<SchoolDTO> findAllByCreatedDate (Pageable pageable, Search search);
    public Long countByCreatedDate (Pageable pageable, Search search);
    public Long countByCreatedDate2(Pageable pageable, Search search);

        public SchoolDTO findByUserId (Long userId);

//    관리자
    public List<School> findByCreatedDate (Pageable pageable);
    public List<SchoolDTO> findBySchoolOnly (Pageable pageable, Search search);
    public List<SchoolDTO> findBySchoolOnlyAsc ();


    public List<SchoolDTO> findSchoolSearch (Pageable pageable, Search search);
    public List<SchoolDTO> findSchoolSearchAsc (Pageable pageable, Search search);
    public List<SchoolDTO> findByBudgetAndSearchAsc (Pageable pageable, Search search);
    public List<SchoolDTO> findByBudgetAndSearch (Pageable pageable, Search search);
}
