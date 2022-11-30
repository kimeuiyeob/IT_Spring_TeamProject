package com.app.milestone.people;

import com.app.milestone.domain.PeopleDTO;
import com.app.milestone.domain.Rank;
import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.entity.*;
import com.app.milestone.repository.*;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@SpringBootTest
@Transactional
@Rollback(false)
public class PeopleTest {
    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    //    개인 샘플 데이터(100명)
    @Test
    public void saveTest() {
        String[] names = {"이지은", "이승철", "윤도현", "이문세", "정은지", "김광석"};
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            PeopleDTO peopleDTO = new PeopleDTO("독수리" + i + "호", "asd@ads.com", names[i % 6], "12341234", "01045614561", 0);
            peopleRepository.save(peopleDTO.toEntity());
        }

    }

    //    개인 한 명의 정보
    @Test
    public void findTest() {
        People people = peopleRepository.findById(105L).get();
        log.info(people.getUserName());
        log.info(people.getPeopleNickname());
    }

    //    ======================================================보육원 좋아요===================================================
    @Test
    public void likeTest() {

    }

    //   =======================================================기부 랭킹================================================
    @Autowired
    private MoneyRepository moneyRepository;
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private DonationRepository donationRepository;

    //    기부금 샘플 데이터
    @Test
    public void moneyTest() {
        int donationCount = 0;

        for (int i = 0; i < 30; i++) {
            // 기부자 피기부자 샘플
            Long giver = 1L + (i % 5);
            Long taker = 105L;
            // 개인 총 기부 횟수 카운트
            People people = peopleRepository.findById(giver).get();
            donationCount = donationRepository.countByPeopleUserId(giver);
            people.update(donationCount);
            // 보육원 총 기부받은 횟수 카운트
            School school = schoolRepository.findById(taker).get();
            donationCount = donationRepository.countBySchoolUserId(taker);
            school.update(donationCount);

            Money money = new Money(school, people, 10000 * (i % 8 + 1));
            moneyRepository.save(money);
        }
    }

    //    방문기부 샘플 데이터
    @Test
    public void visitTest() {
        int donationCount = 0;

        for (int i = 0; i < 30; i++) {
            // 기부자 피기부자 샘플
            Long giver = 1L + (i % 5);
            Long taker = 105L;
            // 개인 총 기부 횟수 카운트
            People people = peopleRepository.findById(giver).get();
            donationCount = donationRepository.countByPeopleUserId(giver);
            people.update(donationCount);
            // 보육원 총 기부받은 횟수 카운트
            School school = schoolRepository.findById(taker).get();
            donationCount = donationRepository.countBySchoolUserId(taker);
            school.update(donationCount);

            Service service = new Service(school, people, LocalDateTime.now());
            serviceRepository.save(service);
        }
    }

    //    개인 기부금 랭킹 정보
    @Test
    public void moneyRankingTest() {
        List<Tuple> tests = peopleRepository.sortByMoneyCash();
        for (Tuple tuple : tests) {
            People people = peopleRepository.findById(tuple.get(1, Long.TYPE)).get();
            log.info("기부금 : " + tuple.get(0, Long.class) + " 기부자 : " + people.getUserName());
        }
    }

    //    개인 보육원 방문기부 횟수 랭킹 정보
    @Test
    public void visitRankingTest() {
        List<Tuple> tests = peopleRepository.sortByVisitRank();
        for (Tuple tuple : tests) {
            People people = peopleRepository.findById(tuple.get(1, Long.TYPE)).get();
            log.info("방문횟수 : " + tuple.get(0, Long.class) + " 기부자 : " + people.getUserName());
        }
    }

    //  재능 기부 횟수 랭킹 정보
//    구현필요함


    //    =====================================================================================================================
    @Test
    public void updateTest() {
        People people = peopleRepository.findById(2L).get();
        people.update("슬픈 뚱이");
    }

    @Test
    public void deleteTest() {
        peopleRepository.deleteById(1L);
    }
}
