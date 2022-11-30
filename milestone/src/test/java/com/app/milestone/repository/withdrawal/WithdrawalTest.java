package com.app.milestone.repository.withdrawal;

import com.app.milestone.domain.WithdrawalDTO;
import com.app.milestone.entity.Withdrawal;
import com.app.milestone.repository.WithdrawalRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

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

    @Test
    public void saveTest() {
        WithdrawalDTO withdrawalDTO = new WithdrawalDTO();
        withdrawalDTO.setWithdrawalReason("서비스 퀄리티가 낮아요");
        withdrawalRepository.save(withdrawalDTO.toEntity());
    }

    @Test
    public void findTest() {
        jpaQueryFactory
                .selectFrom(withdrawal)
                .where(withdrawal.withdrawalId.eq(1L))
                .fetch().stream().map(Withdrawal::toString).forEach(log::info);

    }
}
