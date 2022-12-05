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
    public SchoolDTO findByUserId (Long userId);

//    관리자
    public List<School> findByCreatedDate (Pageable pageable);
//    public List<Tuple> findBySchoolOnly ();
    public List<SchoolDTO> findBySchoolOnly ();
}
