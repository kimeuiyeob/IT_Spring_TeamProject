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
import org.springframework.data.jpa.repository.Modifying;
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
        String[] locations = {"서울 성동구 서울숲길 17", "경기 가평군 가평읍 가랫골길 1", "인천 강화군 강화읍 갑룡길 3", "강원 강릉시 가작로 6", "충북 괴산군 감물면 감물로 7", "충남 계룡시 계룡대로 239", "전북 고창군 고수면 가협길 12", "세종특별자치시 가름로 143", "대전 대덕구 갑천도시고속도로 336", "전남 강진군 강진읍 강진공단길 8", "광주 광산구 가마길 2-21", "부산 강서구 가달1로 7"
                , "울산 남구 갈밭로 3", "대구 남구 경상길 1", "경북 경산시 감못둑길 70", "경남 거제시 거제면 거제남서로 3233", "제주특별자치도 서귀포시 가가로 14"};
        String[] schoolNames = {"희망","사랑","상록","평화","기쁨","행복","소망","명륜","요비","SOS","꿈","파란","해성","신명","해피","광명","천사","나의 사랑","우리 행복","나의 희망","꿈이 가득한","행복 가득"};
        log.info("========================="+locations.length);
        log.info("========================="+schoolNames.length);
        for (int i = 0; i < 107; i++) {
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SchoolDTO schoolDTO = new SchoolDTO();
            schoolDTO.setSchoolName(schoolNames[i % 22] + " 보육원");
            schoolDTO.setSchoolZipcode("222222");
            schoolDTO.setSchoolAddress(locations[i % 17]);
            schoolDTO.setSchoolAddressDetail("주공받공아파트 124층 -12호");
            schoolDTO.setSchoolTeachers(3);
            schoolDTO.setSchoolKids(3);
            schoolDTO.setSchoolBudget(100000);
            schoolDTO.setSchoolBank("하나은행");
            schoolDTO.setSchoolAccount("000000000000");
            schoolDTO.setSchoolPhoneNumber("01012341234");
            schoolDTO.setSchoolQR("https://chart.googleapis.com/chart?cht=qr&chs=200x200&chl=http://localhost:9999/school/donation?userId="+ (101 + i));
            schoolDTO.setSchoolTitle("안녕하세요" + schoolNames[i%22] + "입니다" + i);
            schoolDTO.setSchoolContent("안녕하세요" + schoolNames[i%22] + "보육원 입니다. 잘 부탁드립니다." + i);
            schoolDTO.setUserEmail("asd" + i + "@asd.asd");
            schoolDTO.setUserName("임장원");
            schoolDTO.setUserPassword("cXdlcjEyMzQh");
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

    //    보육원 정보(하나)
    @Test
    public void findByUserId() {
        log.info("" + schoolRepository.findByUserId(5L));
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

//        schoolRepository.findAllByCreatedDate(pageable, search).forEach(o -> log.info("보육원 이름" + o.getSchoolName() + "보육원 주소" + o.getSchoolAddress() + "보육원 등록일" + o + "기부 받은 횟수" + o.getDonationCount()));
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
        schoolRepository.deleteByUserPassword("12341234");
//        jpaQueryFactory.delete(school).where(school.userId.eq(2L)).execute();
    }

}
