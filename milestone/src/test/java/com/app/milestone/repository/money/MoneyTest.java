package com.app.milestone.repository.money;

import com.app.milestone.entity.Money;
import com.app.milestone.entity.People;
import com.app.milestone.entity.School;
import com.app.milestone.repository.DonationRepository;
import com.app.milestone.repository.MoneyRepository;
import com.app.milestone.repository.PeopleRepository;
import com.app.milestone.repository.SchoolRepository;
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
    @Autowired
    private DonationRepository donationRepository;
    @Autowired
    private PeopleRepository peopleRepository;
    @Autowired
    private SchoolRepository schoolRepository;

    //    기부금 샘플 데이터
    @Test
    public void moneyTest() {
        int donationCount = 0;

        for (int i = 0; i < 30; i++) {
            // 기부자 피기부자 샘플
            Long giver = 111L + (i % 5);
            Long taker = 5L;
            // 개인 총 기부 횟수 카운트
            People people = peopleRepository.findById(giver).get();
            donationCount = donationRepository.countByPeopleUserId(giver);
            people.update(donationCount);
            // 보육원 총 기부받은 횟수 카운트
            School school = schoolRepository.findById(taker).get();
            donationCount = donationRepository.countBySchoolUserId(taker);
            school.update(donationCount);

            Money money = new Money(10000L * (i % 8 + 1));
            money.changePeople(people);
            money.changeSchool(school);
            moneyRepository.save(money);
        }
    }

    //    결제
    @Test
    public void saveTest() {
//        Long
    }
}
