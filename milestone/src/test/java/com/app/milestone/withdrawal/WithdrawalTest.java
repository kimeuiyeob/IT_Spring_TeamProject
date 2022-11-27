package com.app.milestone.withdrawal;

import com.app.milestone.domain.NoticeDTO;
import com.app.milestone.domain.WithdrawalDTO;
import com.app.milestone.entity.Notice;
import com.app.milestone.entity.Withdrawal;
import com.app.milestone.repository.NoticeRepository;
import com.app.milestone.repository.WithdrawalRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class WithdrawalTest {
    @Autowired
    private WithdrawalRepository withdrawalRepository;

    private JPAQueryFactory jpaQueryFactory;

    @Test
    public void saveTest() {
        WithdrawalDTO withdrawalDTO = new WithdrawalDTO("이용하고 싶은 서비스가 없어요");
        withdrawalRepository.save(withdrawalDTO.toEntity());
    }

    @Test
    public void findTest() {
        assertThat(withdrawalRepository.findById(3L).get().getWithdrawalId()).isEqualTo(3);
    }
}
