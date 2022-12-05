package com.app.milestone.repository.talent;

import com.app.milestone.domain.QTalentDTO;
import com.app.milestone.domain.Search;
import com.app.milestone.domain.TalentDTO;
import com.app.milestone.entity.People;
import com.app.milestone.entity.QSchool;
import com.app.milestone.entity.School;
import com.app.milestone.entity.Talent;
import com.app.milestone.repository.PeopleRepository;
import com.app.milestone.repository.SchoolRepository;
import com.app.milestone.repository.TalentRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.app.milestone.entity.QDonation.donation;
import static com.app.milestone.entity.QPeople.people;
import static com.app.milestone.entity.QTalent.talent;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class TalentTest {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    @Autowired //SpringBoot가 미리 생성해놓은 객체를 가져다가 자동으로 연결!, 빈에다가 등록, 이걸 해줌으로써 new TalentRepository안해도 된다.
    private TalentRepository talentRepository;
    @Autowired
    private PeopleRepository peopleRepository;
    @Autowired
    private SchoolRepository schoolRepository;

    @Test
    public void save2Test() {

//        String[] talentPlaces = {"서울","경기도","강원도","충청도","전라도","경상도","제주도"};
//        String[] talentTitles = {"코끼리", "거북이", "하마", "기린", "돌고래", "비둘기", "뉴트리아", "뱀", "준성이"};
//        String[] talentContents = {"나는내용입니다", "나는내용일까요?", "나는김의엽입니다", "나는누구일까요?", "이제저도모르겠습니다."};
//        String[] talentCategorys = {"교육", "운동", "음악", "미술","IT"};
//        for (int i = 0; i < 50; i++) {

        TalentDTO talentDTO = new TalentDTO();

        People people = peopleRepository.findById(110L).get();

//            talentDTO.setTalentTitle(talentTitles[i % 8]);
//            talentDTO.setTalentContent(talentContents[i % 5]);
//            talentDTO.setTalentAbleDate(LocalDateTime.now());
//            talentDTO.setTalentCategory(talentCategorys[i % 5]);
//            talentDTO.setTalentPlace(talentPlaces[i % 7]);

        talentDTO.setTalentTitle("안녕10");
        talentDTO.setTalentContent("이건내용10");
        talentDTO.setTalentAbleDate(LocalDateTime.now());
        talentDTO.setTalentCategory("음악");
        talentDTO.setTalentPlace("전라도");

        Talent talent = talentRepository.save(talentDTO.toEntity()); //talentRepository.save(talentDTO.toEntity()) 리턴타입 Entity

        talent.changePeople(people);
//    }
}

    @Test
    public void saveTest() {
        People peopleId = peopleRepository.findById(2L).get();
        TalentDTO talentDTO = new TalentDTO();
        talentDTO.setTalentTitle("안녕");
        talentDTO.setTalentContent("하세용");
        talentDTO.setTalentAbleDate(LocalDateTime.now());
        talentDTO.setTalentCategory("IT");
        talentDTO.setTalentPlace("제주도");

        Talent talent = talentDTO.toEntity();
        talentRepository.save(talent);
        talent.changePeople(peopleId);
    }

    @Test
    public void findTest() {
        List<Talent> UserId12 = jpaQueryFactory
                .select(talent)
                .from(talent, donation, people)
                .where(talent.donationId.eq(donation.donationId))
                .where(donation.people.userId.eq(people.userId))
                .where(people.userId.eq(1L))
                .fetch();
        UserId12.stream().map(Talent::toString).forEach(log::info);
    }

    @Test
    public void findTest2() {
        List<Talent> doantionId202 = jpaQueryFactory
                .select(talent)
                .from(talent, donation)
                .where(talent.donationId.eq(202L))
                .fetch();
        doantionId202.stream().map(Talent::toString).forEach(log::info);
    }

    @Test/*=========================실험중=======================================*/
    public void findTest1() {
        List<TalentDTO> doantionId202 = jpaQueryFactory
                .select(new QTalentDTO(
                        talent.donationId,
                        talent.talentTitle,
                        talent.talentContent,
                        talent.talentAbleDate,
                        talent.talentCategory,
                        talent.talentPlace,
                        talent.people.peopleNickname,
                        talent.school.userId,
                        talent.people.userId
                )).from(talent, donation, people)
                .where(talent.donationId.eq(donation.donationId))
                .where(donation.people.userId.eq(people.userId))
                .where(people.userId.eq(101L))
                .fetch();
        doantionId202.stream().map(TalentDTO::toString).forEach(log::info);
    }


    @Test
    public void find2Test() {
        List<Talent> UserId2 = jpaQueryFactory
                .selectFrom(talent)
                .join(talent.people)
                .fetchJoin()
                .fetch();
        UserId2.stream().map(Talent::toString).forEach(log::info);

    }

    @Test
    public void deleteTest() {
        talentRepository.deleteById(1L);
    }

    @Test
    public void updateTest() {
        Talent talent = talentRepository.findById(2L).get();
        TalentDTO talentDTO = new TalentDTO();
        talentDTO.setTalentTitle("제목");
        talentDTO.setTalentContent("내용");
        talentDTO.setTalentPlace("제주도");
        talentDTO.setTalentAbleDate(LocalDateTime.now());
        talentDTO.setTalentCategory("교육");
        talent.update(talentDTO);
    }

    @Test
    public void findQueryDsl() {
        List<Talent> talents = jpaQueryFactory.selectFrom(talent).fetch();
        for (Talent tal : talents) {
            log.info("아이디넘버 : " + tal.getDonationId() +
                    "제목 : " + tal.getTalentTitle() +
                    "내용 : " + tal.getTalentContent() +
                    "카테고리 : " + tal.getTalentCategory() +
                    "지역 : " + tal.getTalentPlace());
        }
    }

    @Test
    public void findQueryDSL() {
        jpaQueryFactory
                .selectFrom(talent)
                .orderBy(talent.donationId
                        .desc()).fetch().stream()
                .map(Talent::toString).forEach(log::info);
    }

    @Test
    public void deleteQueryDsl() {
        jpaQueryFactory
                .delete(talent)
                .where(talent.donationId.eq(1L))
                .execute();
    }

    @Test
    public void updateQueryDsl() {
        TalentDTO talentDTO = new TalentDTO();
        talentDTO.setTalentTitle("울라라랄");
        talentDTO.setTalentContent("울라라랄라라라");
        talentDTO.setTalentPlace("제주도");
        talentDTO.setTalentAbleDate(LocalDateTime.now());
        talentDTO.setTalentCategory("교육");
        jpaQueryFactory.selectFrom(talent)
                .where(talent.donationId.eq(3L))
                .fetchOne()
                .update(talentDTO);
    }

    @Test //재능 기부 목록(10개씩 , 제목 검색 ,지역 선택, 카테고리 선택, 가능날짜 최신순)
    public void findAllByTalentAbleDateTest() {

        String[] locations = {"강원도", "서울"}; //사용자가 선택한 지역 여러개
        Pageable pageable = PageRequest.of(0, 10); //0페이지에 10개를 가져오겠다.
        Search search = new Search(); //사용자가 선택한 지역을 search에 넣어준다.
        search.setSchoolAddress(new ArrayList<>());

        for (String location : locations) {
            search.getSchoolAddress().add(location);
        }

        search.setTalentTitle("돌고래"); //사용자가 입력한 재능기부 제목
        search.setTalentCategory("운동");

        talentRepository.findAllByTalentAbleDate(pageable, search)
                .forEach(o -> log.info(
                        "  Title : " + o.getTalentTitle() +
                                "  Content : " + o.getTalentContent() +
                                "  Category :" + o.getTalentCategory() +
                                "  Place : " + o.getTalentPlace() +
                                "  AbleDate : " + o.getTalentAbleDate()));
    }

    /*===============================   queryDsl 기본적인 사용 방법   ================================*/

//    public vodid queryDslTest() {
//        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager) //이건 QueryDslConfig에 @Bean으로 등록시켜논거를 @Autowirde를 통해서 사용한다.
//        queryFactory.select(Quser.user) //select와 from절이 같을때 .selectFrom(Quser.user)이렇게 한번에 받을수 있다.
//                .from(Quser.user)
//                .where(Quser.user)
//                .fetchOne(); //리턴하는게 값이 하나면 .fetchOne(), 많으면 .fetch()로 한다.
//        asserThat(Quser.user.getUserNamer()).isEqualTo("김의엽")
//    }

    /*============================================================================================*/
}


