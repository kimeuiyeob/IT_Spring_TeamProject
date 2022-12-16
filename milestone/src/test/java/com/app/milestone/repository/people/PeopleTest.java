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
        String[] names = {"이지은", "이승철", "윤도현", "이문세", "정은지", "김광석", "김세정", "김경호", "유인나", "유해진", "마동석", "정우성", "이정재", "유재석", "이광수", "송지효", "김종국", "정호석", "전소민", "한동석", "김유정", "김태희", "김태희", "한가인", "전지현", "성은지", "정재훈", "문승희", "홍준성", "홍수현", "김인영", "배다빈", "박종우", "김민혁"};
//        log.info("==============================="+names.length);
        String[] nickNames = {"독수리", "흰개미", "고구마"};
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            PeopleDTO peopleDTO = new PeopleDTO();
            peopleDTO.setPeopleNickname(nickNames[i % 3] + i + "호");
            peopleDTO.setUserEmail("qwe" + i + "@qwe.qwe");
            peopleDTO.setUserName(names[i % 34]);
            peopleDTO.setUserPassword("cXdlcjEyMzQh");
            peopleDTO.setUserPhoneNumber("01011112222");

            peopleRepository.save(peopleDTO.toEntity());
        }

    }

    @Test
    public void findOneTest() {
        log.info("" + peopleRepository.findById(132L).get());
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
    public void likeFindByPeopleTest() {
        String text = "";
        for (Like o : likeRepository.findByPeopleUserId(105L)) {
            text += "\n" + o.getSchool().getUserId();
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
            Long giver = 1L + (i % 11);
            Long taker = 105L;
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

    //    방문기부 샘플 데이터
    @Test
    public void visitTest() {
        int donationCount = 0;

        for (int i = 0; i < 30; i++) {
            // 기부자 피기부자 샘플
            Long giver = 10L + (i % 11);
            Long taker = 105L;
            // 개인 총 기부 횟수 카운트
            People people = peopleRepository.findById(giver).get();
            donationCount = donationRepository.countByPeopleUserId(giver);
            people.update(donationCount);
            // 보육원 총 기부받은 횟수 카운트
            School school = schoolRepository.findById(taker).get();
            donationCount = donationRepository.countBySchoolUserId(taker);
            school.update(donationCount);

            Service service = new Service(LocalDateTime.now());
            service.changePeople(people);
            service.changeSchool(school);
            serviceRepository.save(service);
        }
    }

    //    재능기부 샘플 데이터
    @Test
    public void talentTest() {
        int donationCount = 0;
        String[] talentTitle = {"저는 헬스 3년차 태릉인 입니다.", "피아노 선생님 입니다.", "이 바닥에 소문난 IT절대 고수 한동석 입니다.", "저는 국어 선생님 입니다.", "미술인생 20년 입니다."};
        String[] talentContent = {"저는 헬스 3년차 태릉인 입니다. 제가 아이들에게 운동을 가르치겠습니다.", "아이들에게 피아노를 가르치고 싶어요.", "자바가 뭔지 차근 차근 가르칠게요.", "아이들에게 언어와 한국말의 아름다움을 전달 할게요.", "그림을 배우면 아이들이 좋아할 거예요"};
        String[] category = {"운동", "음악", "IT", "교육", "미술"};
        String[] location = {"서울", "경기도", "강원도", "충청도", "전라도", "경상도", "제주도"};

        for (int i = 0; i < 69; i++) {
            // 기부자 피기부자 샘플
            Long giver = 20L + (i % 11);
            Long taker = 110L;
            // 개인 총 기부 횟수 카운트
            People people = peopleRepository.findById(giver).get();
            donationCount = donationRepository.countByPeopleUserId(giver);
            people.update(donationCount);
            // 보육원 총 기부받은 횟수 카운트
            School school = schoolRepository.findById(taker).get();
            donationCount = donationRepository.countBySchoolUserId(taker);
            school.update(donationCount);

            Talent talent = new Talent(talentTitle[i % 5], talentContent[i % 5], LocalDateTime.of(2023, 2, 10, 0, 0, 0), category[i % 5], location[i % 7]);
            talent.changePeople(people);
            if (i % 2 == 0) {
                talent.changeSchool(school);
            }
            talentRepository.save(talent);
        }
    }

    //    =================================실제 기부===================================

    //    개인 기부금 랭킹 정보
//    MoneyTest로 옮길 예정
//    @Test
//    public void moneyRankingTest() {
//        List<Tuple> tests = peopleRepository.sortByMoneyCash();
//        String text = "";
//
//        for (Tuple tuple : tests) {
//            People people = peopleRepository.findById(tuple.get(1, Long.TYPE)).get();
//            text += "\n기부금 : " + tuple.get(0, Long.class) + " 기부자 : " + people.getUserName();
//        }
//        log.info(text);
//    }

    //    개인 보육원 방문기부 횟수 랭킹 정보
//    ServiceTest로 옮길 예정
//    @Test
//    public void visitRankingTest() {
//        List<Tuple> tests = peopleRepository.sortByVisitRank();
//        String text = "";
//
//        for (Tuple tuple : tests) {
//            People people = peopleRepository.findById(tuple.get(1, Long.TYPE)).get();
//            text += "\n방문횟수 : " + tuple.get(0, Long.class) + " 기부자 : " + people.getUserName();
//        }
//        log.info(text);
//    }

    //  재능 기부 횟수 랭킹 정보
//    @Test
//    public void talentRankingTest() {
//        List<Tuple> tests = peopleRepository.sortBytalentRank();
//        String text = "";
//        for (Tuple tuple : tests) {
//            People people = peopleRepository.findById(tuple.get(1, Long.TYPE)).get();
////            log.info("재능기부횟수 : " + tuple.get(0, Long.class) + " 기부자 : " + people.getUserName());
//            text += "\n재능기부횟수 : " + tuple.get(0, Long.class) + " 기부자 : " + people.getUserName();
//        }
//        log.info(text);
//    }

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
