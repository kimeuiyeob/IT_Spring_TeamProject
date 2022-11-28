package com.app.milestone.money;

import com.app.milestone.domain.MoneyDTO;
import com.app.milestone.domain.NoticeDTO;
import com.app.milestone.entity.Money;
import com.app.milestone.entity.Notice;
import com.app.milestone.entity.User;
import com.app.milestone.repository.DonationRepository;
import com.app.milestone.repository.MoneyRepository;
import com.app.milestone.repository.UserRepository;
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
public class MoneyTest {
    @Autowired
    private MoneyRepository moneyRepository;

    private JPAQueryFactory jpaQueryFactory;

//    @Test
//    public void saveTest() {
//        MoneyDTO moneyDTO = new MoneyDTO(3000000);
//        moneyRepository.save(moneyDTO.toEntity());
//    }
//
//    @Test
//    public void findTest() {
//        assertThat(moneyRepository.findById(4L).get().getMoneyCash()).isEqualTo(3000000);
//    }
}
