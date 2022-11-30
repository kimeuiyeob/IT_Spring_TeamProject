package com.app.milestone.repository.withdrawal;

import com.app.milestone.domain.WithdrawalDTO;
import com.app.milestone.entity.Withdrawal;
import com.app.milestone.repository.WithdrawalRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static com.app.milestone.entity.QNotice.notice;
import static com.app.milestone.entity.QWithdrawal.withdrawal;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class WithdrawalTest {
    @Autowired
    private WithdrawalRepository withdrawalRepository;
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

//    회원탈퇴 저장
    @Test
    public void saveTest() {
        WithdrawalDTO withdrawalDTO = new WithdrawalDTO();
        withdrawalDTO.setWithdrawalReason("서비스 퀄리티가 낮아요");
//        withdrawalDTO.setWithdrawalUserType("보육원");                   //사용자 형태
        withdrawalRepository.save(withdrawalDTO.toEntity());
    }

//    회원탈퇴 검색어(사용자 형태, 탈퇴이유)로 찾기
    @Test
    public void findBySearchTest() {
    //        assertThat(noticeRepository.findById(1L).get().getNoticeTitle()).isEqualTo("[점검]긴급 점검입니다.");
        jpaQueryFactory.selectFrom(withdrawal)
                .where(withdrawal.withdrawalReason.contains("퀄리티가"))
//                .where(withdrawal.withdrawalUserType.contains("보육"))    //사용자 형태
                .fetch().forEach(withdrawal->log.info("검색어로 찾아온 탈퇴 사유 : "+withdrawal.getWithdrawalReason()));
    }

//    회원탈퇴 필터(탈퇴날짜, 탈퇴이유)로 찾기
    @Test
    public void findTest() {
//        jpaQueryFactory
//                .selectFrom(withdrawal)
//                .where(withdrawal.withdrawalId.eq(1L))
//                .fetch().stream().map(Withdrawal::toString).forEach(log::info);

    }
}
