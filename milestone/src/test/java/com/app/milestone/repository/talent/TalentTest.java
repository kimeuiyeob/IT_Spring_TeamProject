package com.app.milestone.repository.talent;

import com.app.milestone.domain.Search;
import com.app.milestone.domain.TalentDTO;
import com.app.milestone.entity.*;
import com.app.milestone.repository.PeopleRepository;
import com.app.milestone.repository.TalentRepository;
import com.app.milestone.type.Place;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

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

    @Test
    public void save2Test() {
        Place[] talentPlaces = {Place.경상도, Place.서울, Place.강원도, Place.제주도, Place.충청도};
        String[] talentTitles = {"코끼리", "거북이", "하마", "기린", "돌고래", "비둘기", "뉴트리아", "뱀"};
        String[] talentContents = {"나는내용입니다", "나는내용일까요?", "나는김의엽입니다", "나는누구일까요?", "이제저도모르겠습니다."};
        String[] talentCategorys = {"운동", "음악", "미술", "IT"};


        for (int i = 0; i < 50; i++) {
            TalentDTO talentDTO = new TalentDTO();
            talentDTO.setTalentTitle(talentTitles[i % 8]);
            talentDTO.setTalentContent(talentContents[i % 5]);
            talentDTO.setTalentAbleDate(LocalDateTime.now());
            talentDTO.setCategory(talentCategorys[i % 4]);
//            talentDTO.setPlace(talentPlaces[i % 5]);
            talentRepository.save(talentDTO.toEntity());
        }
    }

    @Test
    public void saveTest() {
        People peopleId = peopleRepository.findById(2L).get();
        TalentDTO talentDTO = new TalentDTO();
        talentDTO.setTalentTitle("안녕");
        talentDTO.setTalentContent("하세용");
        talentDTO.setTalentAbleDate(LocalDateTime.now());
        talentDTO.setCategory("IT");
//        talentDTO.setPlace(Place.제주도);

        Talent talent = talentDTO.toEntity();
        talentRepository.save(talent);
        talent.changePeople(peopleId);
    }

    @Test
    public void findQueryDsl2() {

    }

//    @Test
//    public void findJPQL() {
//        talentRepository
//    }


    @Test
    public void findTest() {
//        List<Talent> asd = jpaQueryFactory
//                .select(talent,QPeople.people,QDonation.donation).where(talent.donationId=QDonation.donation.donationId),
//        (QPeople.people.userId = QDonation.donation.people.userId),()
        List<Talent> yoby = jpaQueryFactory
                .select(talent)
                .from(talent, donation, people)
                .where(talent.donationId.eq(donation.donationId))
                .where(donation.people.userId.eq(people.userId))
                .where(people.userId.eq(12L))
                .fetch();
        yoby.stream().map(Talent::toString).forEach(log::info);

    }

  /*  @Test
    public void findTest() {
        Talent talent = talentRepository.findById(1L).get();
    }*/

     /*   @Test
    public void deleteTest() {
        talentRepository.deleteById(1L);
    }*/


    @Test
    public void updateTest() {
        Talent talent = talentRepository.findById(2L).get();
        TalentDTO talentDTO = new TalentDTO();
        talentDTO.setTalentTitle("제목");
        talentDTO.setTalentContent("내용");
//        talentDTO.setPlace(Place.제주도);
        talentDTO.setTalentAbleDate(LocalDateTime.now());
        talentDTO.setCategory("교육");
        talent.update(talentDTO);
    }

    @Test
    public void findQueryDsl() {
        List<Talent> tals = jpaQueryFactory.selectFrom(talent).fetch();
        for (Talent tal : tals) {
            log.info("아이디넘버 : " + tal.getDonationId() +
                    "제목 : " + tal.getTalentTitle() +
                    "내용 : " + tal.getTalentContent() +
                    "카테고리 : " + tal.getCategory() +
                    "지역 : " + tal.getPlace());
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
//        talentDTO.setPlace(Place.제주도);
        talentDTO.setTalentAbleDate(LocalDateTime.now());
        talentDTO.setCategory("교육");
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
        search.setTalentCategory(new ArrayList<>());

        for (String location : locations) {
            search.getSchoolAddress().add(location);
        }

        search.setTalentTitle("돌고래"); //사용자가 입력한 재능기부 제목
        search.getTalentCategory().add("운동");
        talentRepository.findAllByTalentAbleDate(pageable, search)
                .forEach(o -> log.info(
                        "  Title : " + o.getTalentTitle() +
                        "  Content : " + o.getTalentContent() +
                        "  Category :" + o.getCategory() +
                        "  Place : " + o.getPlace() +
                        "  AbleDate : " + o.getTalentAbleDate()));
    }


}
