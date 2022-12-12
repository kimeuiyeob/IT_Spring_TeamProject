package com.app.milestone.service;

import com.app.milestone.domain.PeopleDTO;
import com.app.milestone.domain.Search;
import com.app.milestone.domain.WithdrawalDTO;
import com.app.milestone.entity.Withdrawal;
import com.app.milestone.repository.WithdrawalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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

    public Page<WithdrawalDTO> withdrawalListSearch(Integer page, String reason) {
        if (page == null) page = 0;
        Pageable pageable = PageRequest.of(page, 7);
        if (reason == null) {
            reason = null;
        }
        List<WithdrawalDTO> list = withdrawalRepository.findWithdrawalSearch(pageable, reason);
        Page<WithdrawalDTO> withdrawal = new PageImpl<>(list, pageable, Integer.valueOf("" + withdrawalRepository.countByCreatedDate(pageable, reason)));
        return withdrawal;
    }


    public Page<WithdrawalDTO> withdrawalListSearchAsc(Integer page, String reason) {
        if (page == null) page = 0;
        Pageable pageable = PageRequest.of(page, 7);
        if (reason == null) {
            reason = null;
        }
        List<WithdrawalDTO> list = withdrawalRepository.findWithdrawalSearchAsc(pageable, reason);
        Page<WithdrawalDTO> withdrawal = new PageImpl<>(list, pageable, Integer.valueOf("" + withdrawalRepository.countByCreatedDate(pageable, reason)));
        return withdrawal;
    }


}
