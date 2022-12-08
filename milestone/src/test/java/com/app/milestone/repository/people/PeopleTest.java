package com.app.milestone.repository.people;

import com.app.milestone.domain.PeopleDTO;
import com.app.milestone.entity.*;
import com.app.milestone.repository.*;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.app.milestone.entity.QPeople.*;


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
//            PeopleDTO peopleDTO = new PeopleDTO("독수리" + i + "호", "asd@ads.com", names[i % 6], "12341234", "01045614561", 0);
//            peopleRepository.save(peopleDTO.toEntity());
        }

    }

    @Test
    public void findOneTest(){
        log.info(""+peopleRepository.findById(132L).get());
    }

    //    개인 한 명의 정보
    @Test
    public void findTest() {
//        People people = peopleRepository.findById(122L).get();
//        log.info(people.getUserName());
//        log.info(people.getPeopleNickname());
        jpaQueryFactory
                .select(people.userEmail, people.userName, people.userPhoneNumber, people.peopleNickname)
                .from(people)
                .where(people.userId.eq(123L))
                .fetch()
                .stream().map(Object::toString).forEach(log::info);
    }

    //    ======================================================보육원 좋아요===================================================
    @Autowired
    private LikeRepository likeRepository;

    //    좋아요 누름
    @Test
    public void likeTest() {
        for (int i = 0; i < 2; i++) {
            //        세션에서 받음
            People people = peopleRepository.findById(105L).get();
//        화면에서 받음
            School school = schoolRepository.findById(8L).get();
            Like like = new Like(school, people);
            likeRepository.save(like);
        }
    }

    @Test
    public void likeCountTest() {
        Long schoolId = 5L;
        log.info("" + likeRepository.countBySchoolUserId(schoolId));
    }

    @Test
    public void likeFindByPeopleTest(){
        String text = "";
        for (Like o :likeRepository.findByPeopleUserId(105L)){
            text += "\n"+o.getSchool().getUserId();
        }
        log.info(text);
    }

    //   =======================================================기부 랭킹================================================
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private DonationRepository donationRepository;
    @Autowired
    private MoneyRepository moneyRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private TalentRepository talentRepository;


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

            Money money = new Money(school, people, 10000L * (i % 8 + 1));
            moneyRepository.save(money);
        }
    }

    //    방문기부 샘플 데이터
    @Test
    public void visitTest() {
        int donationCount = 0;

        for (int i = 0; i < 30; i++) {
            // 기부자 피기부자 샘플
            Long giver = 125L + (i % 5);
            Long taker = 5L;
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

    //    재능기부 샘플 데이터
    @Test
    public void talentTest() {
        int donationCount = 0;

        for (int i = 0; i < 18; i++) {
            // 기부자 피기부자 샘플
            Long giver = 101L + (i % 5);
            Long taker = 5L;
            // 개인 총 기부 횟수 카운트
            People people = peopleRepository.findById(giver).get();
            donationCount = donationRepository.countByPeopleUserId(giver);
            people.update(donationCount);
            // 보육원 총 기부받은 횟수 카운트
            School school = schoolRepository.findById(taker).get();
            donationCount = donationRepository.countBySchoolUserId(taker);
            school.update(donationCount);

            Talent talent = new Talent(school, people, "재능제목", "재능내용", LocalDateTime.now(), "교육", "서울");
            talentRepository.save(talent);
        }
    }

    //    =================================실제 기부===================================

    //    개인 기부금 랭킹 정보
    @Test
    public void moneyRankingTest() {
        List<Tuple> tests = peopleRepository.sortByMoneyCash();
        String text = "";

        for (Tuple tuple : tests) {
            People people = peopleRepository.findById(tuple.get(1, Long.TYPE)).get();
            text += "\n기부금 : " + tuple.get(0, Long.class) + " 기부자 : " + people.getUserName();
        }
        log.info(text);
    }

    //    개인 보육원 방문기부 횟수 랭킹 정보
    @Test
    public void visitRankingTest() {
        List<Tuple> tests = peopleRepository.sortByVisitRank();
        String text = "";

        for (Tuple tuple : tests) {
            People people = peopleRepository.findById(tuple.get(1, Long.TYPE)).get();
            text += "\n방문횟수 : " + tuple.get(0, Long.class) + " 기부자 : " + people.getUserName();
        }
        log.info(text);
    }

    //  재능 기부 횟수 랭킹 정보
    @Test
    public void talentRankingTest() {
        List<Tuple> tests = peopleRepository.sortBytalentRank();
        String text = "";
        for (Tuple tuple : tests) {
            People people = peopleRepository.findById(tuple.get(1, Long.TYPE)).get();
//            log.info("재능기부횟수 : " + tuple.get(0, Long.class) + " 기부자 : " + people.getUserName());
            text += "\n재능기부횟수 : " + tuple.get(0, Long.class) + " 기부자 : " + people.getUserName();
        }
        log.info(text);
    }

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
