package com.app.milestone.repository;

import com.app.milestone.domain.PeopleDTO;
import com.app.milestone.domain.Search;
import com.app.milestone.domain.WithdrawalDTO;
import com.app.milestone.entity.Withdrawal;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WithdrawalCustomRepository {
    public List<WithdrawalDTO> findByCreatedDate (Pageable pageable);
    public List<WithdrawalDTO> findByCreatedDateAsc ();

    public Long countByCreatedDate (Pageable pageable, Search search);
    public List<WithdrawalDTO> findWithdrawalSearch(Pageable pageable, Search search);
    public List<WithdrawalDTO> findWithdrawalSearchAsc(Pageable pageable,Search search);
}
