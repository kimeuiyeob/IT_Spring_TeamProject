package com.app.milestone.repository;

import com.app.milestone.domain.WithdrawalDTO;
import com.app.milestone.entity.Withdrawal;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WithdrawalCustomRepository {
    public List<Withdrawal> findByCreatedDate (Pageable pageable);
//    public List<WithdrawalDTO> findByCreatedDate (Pageable pageable);

}
