package com.app.milestone.service;

import com.app.milestone.domain.WithdrawalDTO;
import com.app.milestone.entity.Withdrawal;
import com.app.milestone.repository.WithdrawalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WithdrawalService {
    private final WithdrawalRepository withdrawalRepository;

    //    전체 탈퇴회원 목록(최신순)
    public List<WithdrawalDTO> withdrawalList(Pageable pageable){
        return withdrawalRepository.findByCreatedDate(pageable);
    }

    //  전체 탈퇴회원 목록(오래된 순)
    public List<WithdrawalDTO> withdrawalListAsc(){
        return withdrawalRepository.findByCreatedDateAsc();
    }
}
