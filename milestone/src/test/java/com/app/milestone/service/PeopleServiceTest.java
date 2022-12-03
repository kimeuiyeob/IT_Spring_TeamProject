package com.app.milestone.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class PeopleServiceTest {
    @Autowired
    private PeopleService peopleService;

    //    개인회원 한 명의 정보
    @Test
    public void onesInfoTest() {
        log.info(peopleService.onesInfo(105L) + "");
    }

    //    좋아요 누름
    @Test
    public void likeSchoolTest() {
        List<Long> userIds = new ArrayList<>();
        userIds.add(5L);
        userIds.add(105L);
        peopleService.likeSchool(userIds);
    }

    //    기부금 랭킹
    @Test
    public void donationMoneyRankingTest() {
        log.info("" + peopleService.donationMoneyRanking());
    }

    //    방문횟수 랭킹
    @Test
    public void donationVisitRankingTest() {
        log.info("" + peopleService.donationVisitRanking());
    }

    //    재능기부 횟수 랭킹
    @Test
    public void donationTalentRankingTest() {
        log.info("" + peopleService.donationTalentRanking());
    }
}
