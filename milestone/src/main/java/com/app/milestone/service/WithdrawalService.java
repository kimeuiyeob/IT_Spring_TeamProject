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

    public Long withdrawalListCount(Pageable pageable, Search search) {
        return withdrawalRepository.countByCreatedDate(pageable, search);
    }

    public Page<WithdrawalDTO> withdrawalListSearch(Integer page, Search search) {
        if (page == null) page = 0;
        Pageable pageable = PageRequest.of(page, 7);

        if (search.getWithdrawalReason() == null) {
            search.setWithdrawalReason(null);
        }

        List<WithdrawalDTO> list = withdrawalRepository.findWithdrawalSearch(pageable, search);
        int start = list.size() >= (int) pageable.getOffset() ? (int) pageable.getOffset() : (int) pageable.getOffset() - 10;
        int end = Math.min((start + pageable.getPageSize()), list.size());

        Page<WithdrawalDTO> withdrawals = new PageImpl<>(list.subList(start, end), pageable, Integer.valueOf("" + withdrawalRepository.countByCreatedDate(pageable, search)));

        return withdrawals;
    }
    public Page<WithdrawalDTO> withdrawalListSearchAsc(Integer page, Search search) {
        if (page == null) page = 0;
        Pageable pageable = PageRequest.of(page, 7);

        if (search.getWithdrawalReason() == null) {
            search.setWithdrawalReason(null);
        }

        List<WithdrawalDTO> list = withdrawalRepository.findWithdrawalSearchAsc(pageable, search);
        int start = list.size() >= (int) pageable.getOffset() ? (int) pageable.getOffset() : (int) pageable.getOffset() - 10;
        int end = Math.min((start + pageable.getPageSize()), list.size());

        Page<WithdrawalDTO> withdrawals = new PageImpl<>(list.subList(start, end), pageable, Integer.valueOf("" + withdrawalRepository.countByCreatedDate(pageable, search)));

        return withdrawals;
    }


}
