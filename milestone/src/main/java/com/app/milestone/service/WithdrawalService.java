package com.app.milestone.service;

import com.app.milestone.domain.WithdrawalDTO;
import com.app.milestone.entity.Withdrawal;
import com.app.milestone.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WithdrawalService {

    private final WithdrawalRepository withdrawalRepository;

    private final DonationRepository donationRepository;
    private final FileRepository fileRepository;
    private final AlarmRepository alarmRepository;
    private final ReplyRepository replyRepository;

    /*==========================정서림===========================*/
    //    전체 탈퇴회원 목록
    public List<WithdrawalDTO> withdrawalList(Pageable pageable){
        return withdrawalRepository.findByCreatedDate(pageable);
    }

    /*==========================정서림===========================*/
    //  탈퇴회원 검색목록
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

    /*==========================정서림===========================*/
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

    //  회원탈퇴시 탈퇴이유 저장
    @Transactional
    public void insertReason(WithdrawalDTO withdrawalDTO) {
        Withdrawal entity = withdrawalDTO.toEntity();
        withdrawalRepository.save(entity);
    }

    //  탈퇴시 모든 정보삭제
    @Transactional
    public void deleteEverything(Long userId) {
        donationRepository.deleteByPeopleUserId(userId);
        donationRepository.deleteBySchoolUserId(userId);
        alarmRepository.deleteByGiverUserId(userId);
        alarmRepository.deleteByTakerUserId(userId);
        fileRepository.deleteByUserId(userId);
        replyRepository.deleteBySchoolUserId(userId);
        replyRepository.deleteByUserUserId(userId);
    }

}
