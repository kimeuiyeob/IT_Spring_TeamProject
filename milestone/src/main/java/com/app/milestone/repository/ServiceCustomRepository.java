package com.app.milestone.repository;

import com.app.milestone.domain.MoneyDTO;
import com.app.milestone.domain.ServiceDTO;
import com.querydsl.core.Tuple;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ServiceCustomRepository {
    public List<Tuple> sortByVisitRank();

    public List<ServiceDTO> findServiceSearch (Pageable pageable, String keyword);
    public List<ServiceDTO> findServiceSearchAsc (Pageable pageable, String keyword);
    public Long countByCreatedDate(Pageable pageable, String keyword);

}
