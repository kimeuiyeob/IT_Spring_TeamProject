package com.app.milestone.repository;

import com.app.milestone.domain.Search;
import com.app.milestone.entity.Talent;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TalentCustomRepository {

    public List<Talent> findAllByTalentAbleDate (Pageable pageable, Search search);
}
