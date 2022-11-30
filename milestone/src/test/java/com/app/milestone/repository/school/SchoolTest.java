package com.app.milestone.repository.school;

import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.domain.Search;
import com.app.milestone.embeddable.Address;
import com.app.milestone.embeddable.Introduce;
import com.app.milestone.entity.Money;
import com.app.milestone.entity.School;
import com.app.milestone.repository.DonationRepository;
import com.app.milestone.repository.MoneyRepository;
import com.app.milestone.repository.PeopleRepository;
import com.app.milestone.repository.SchoolRepository;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class SchoolTest {
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    //  보육원 샘플데이터 (100개)추가
    @Test
    public void saveTest() {
        String[] locations = {"바다", "산", "하늘", "들판", "사막", "동굴"};
        String[] schoolNames = {"코끼리", "거북이", "하마", "기린", "돌고래", "비둘기", "뉴트리아", "뱀"};
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SchoolDTO schoolDTO = new SchoolDTO();
            Address address = new Address();
            Introduce introduce = new Introduce();
            schoolDTO = new SchoolDTO();
            address.setSchoolZipcode("11111");
            address.setSchoolAddress("바다");
            address.setSchoolAddressDetail("용궁1동");
            introduce.setSchoolTitle("안녕하세요");
            introduce.setSchoolContent("반가워요");
            schoolDTO.setSchoolName(schoolNames[i % 8] + " 보육원");
            schoolDTO.setSchoolZipcode("222222");
            schoolDTO.setSchoolAddress(locations[i % 6]);
            schoolDTO.setSchoolAddressDetail("봉우리1동");
            schoolDTO.setSchoolTeachers(3);
            schoolDTO.setSchoolKids(3);
            schoolDTO.setSchoolBudget(100000);
            schoolDTO.setSchoolBank("하나은행");
            schoolDTO.setSchoolAccount("000000000000");
            schoolDTO.setSchoolPhoneNumber("12312341234");
            schoolDTO.setSchoolQR(null);
            schoolDTO.setSchoolTitle("치지직");
            schoolDTO.setSchoolContent("두루미두루미");
            schoolDTO.setUserEmail("qwe@qwe.qwe");
            schoolDTO.setUserName("황지수");
            schoolDTO.setUserPassword("12341234");
            schoolDTO.setUserPhoneNumber("01012341234");
            schoolDTO.setUserProfile(null);
            schoolDTO.setDonationCount(i);

            schoolRepository.save(schoolDTO.toEntity());
        }
    }

    //  메인 도움이 필요해요 (5개, 기부 받은 횟수가 가장 적은순)
    @Test
    public void findAllByDonationCountTest() {
        Pageable pageable = PageRequest.of(0, 5);
//        schoolRepository.findAllByDonationCount(pageable).forEach(o -> log.info("보육원 이름" + o.getSchoolName() + "기부 받은 횟수" + o.getDonationCount()));
    }

    //  보육원 목록(10개, 지역과 보육원 이름 검색 조건추가 후 최신순)
    @Test
    public void findAllByCreatedDateTest() {
//      사용자가 선택한 지역 여러개
        String[] locs = {"바다", "동굴", "사막"};

//      0페이지에 100개를 가져오겠다.
        Pageable pageable = PageRequest.of(0, 100);
//      사용자가 선택한 지역을 search에 넣어줌
        Search search = new Search();
        search.setSchoolAddress(new ArrayList<>());
        for (String loc : locs) {
            search.getSchoolAddress().add(loc);
        }
//      사용자가 입력한 보육원 이름을 search에 넣어줌
        search.setSchoolName("뉴트");
//        search.setSchoolName("고래");

        schoolRepository.findAllByCreatedDate(pageable, search).forEach(o -> log.info("보육원 이름" + o.getSchoolName() + "보육원 주소" + o.getAddress().getSchoolAddress() + "보육원 등록일" + o.getCreatedDate() + "기부 받은 횟수" + o.getDonationCount()));
    }

    //    총 보육원 수 조회
    @Test
    public void countTest() {
        log.info("총 보육원 수" + schoolRepository.countBy());
    }

    //   ================================================== 보육원 상세보기 ==============================================
    @Autowired
    private DonationRepository donationRepository;
    @Autowired
    private MoneyRepository moneyRepository;
    @Autowired
    private PeopleRepository peopleRepository;

    @Test
    public void schoolDetailTest() {
        School school = schoolRepository.findById(1L).get();
        log.info("보육원 이름 : " + school.getSchoolName() +
                "보육원 제목 : " + school.getIntroduce().getSchoolTitle() +
                "보육원 소개글 : " + school.getIntroduce().getSchoolContent());
    }

    //    보육원 상세보기의 최근기부
    @Test
    public void recentDonationTest() {
        List<Money> recentMoney = moneyRepository.findByOrderByCreatedDateDesc();
        String text = "";

        for (Money money : recentMoney) {
            String name = money.getPeople().getUserName();
            text += "\n최근 기부일 : " + money.getCreatedDate() + " 기부자 : " + name + " 기부금 : " + money.getMoneyCash();
        }
        log.info(text);
    }

    //  하나의 보육원 기부금 랭킹
    @Test
    public void moneyRankingByOneTest() {
        List<Tuple> tuples = moneyRepository.moneyRankingByOne(105L);
        String text = "";

        for (Tuple tuple : tuples) {
            String name = peopleRepository.findById(tuple.get(1, Long.class)).get().getUserName();
            text += "\n기부자 : " + name + "기부금 : " + tuple.get(0, Long.class);
        }
        log.info(text);
    }


    @Test
    public void updateTest() {
        Address address = new Address();
        address.setSchoolZipcode("12121212");
        address.setSchoolAddress("하늘");
        address.setSchoolAddressDetail("뭉개구름1동");
        Introduce introduce = new Introduce();
        introduce.setSchoolTitle("방가방가");
        introduce.setSchoolContent("반가워요");
//        schoolRepository.findById(3L).get().update("asdf@adsf.asdf", "황지수", "12341234", "01074127412", null, "하마보육원", address, 3, 3, 10000, "국민은행", "1111111111", "028192030",null, introduce);
    }

    @Test
    public void deleteTest() {
//        jpaQueryFactory.delete(school).where(school.userId.eq(2L)).execute();
    }

}
