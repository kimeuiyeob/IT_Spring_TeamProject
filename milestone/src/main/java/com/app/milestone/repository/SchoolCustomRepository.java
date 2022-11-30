package com.app.milestone.repository;

import com.app.milestone.domain.Search;
import com.app.milestone.entity.School;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SchoolCustomRepository {
//    public List<School> findAllByDonationCount (Pageable pageable);
    public List<School> findAllByDonationCount ();
    public List<School> findAllByCreatedDate (Pageable pageable, Search search);
}
