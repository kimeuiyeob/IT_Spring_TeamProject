package com.app.milestone.service;

import com.app.milestone.domain.*;
import com.app.milestone.entity.*;
import com.app.milestone.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class SampleData {
    @Autowired
    private PeopleRepository peopleRepository;
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private DonationRepository donationRepository;
    @Autowired
    private MoneyService moneyService;
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private TalentService talentService;


    private Random random = new Random();

    //    개인 샘플 데이터(105명)
    @Test
    public void savePeople() {
        String[] names = {"이지은", "이승철", "윤도현", "이문세", "정은지", "김광석", "김세정", "김경호", "유인나", "유해진", "마동석", "정우성", "이정재", "유재석", "이광수", "송지효", "김종국", "정호석", "전소민", "한동석", "김유정", "김태희", "김태희", "한가인", "전지현", "성은지", "정재훈", "문승희", "홍준성", "홍수현", "김인영", "배다빈", "박종우", "김민혁"};
//        log.info("==============================="+names.length);
        String[] nickNames = {"독수리", "흰개미", "고구마"};
        for (int i = 0; i < 105; i++) {
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
            peopleDTO.setUserPhoneNumber("010" + String.format("%08d", random.nextInt(99999999)));

            peopleRepository.save(peopleDTO.toEntity());
        }
    }

    //  보육원 샘플데이터 (107개)추가
    @Test
    public void saveSchool() {
        String[] locations = {"서울 성동구 서울숲길 17", "경기 가평군 가평읍 가랫골길 1", "인천 강화군 강화읍 갑룡길 3", "강원 강릉시 가작로 6", "충북 괴산군 감물면 감물로 7", "충남 계룡시 계룡대로 239", "전북 고창군 고수면 가협길 12", "세종특별자치시 가름로 143", "대전 대덕구 갑천도시고속도로 336", "전남 강진군 강진읍 강진공단길 8", "광주 광산구 가마길 2-21", "부산 강서구 가달1로 7"
                , "울산 남구 갈밭로 3", "대구 남구 경상길 1", "경북 경산시 감못둑길 70", "경남 거제시 거제면 거제남서로 3233", "제주특별자치도 서귀포시 가가로 14"};
        String[] schoolNames = {"희망", "사랑", "상록", "평화", "기쁨", "행복", "소망", "명륜", "요비", "SOS", "꿈", "파란", "해성", "신명", "해피", "광명", "천사", "나의 사랑", "우리 행복", "나의 희망", "꿈이 가득한", "행복 가득"};
        String[] addressDetail = {"샛별 빌라", "다래 빌라", "가람 빌라", "미리내 빌라", "라온 빌라", "나눔 주택", "행복이 가득한 집", "햇살이 가득한 집", "햇살 좋은 집", "사랑 가득한 집", "온누리 빌라", "피앙세 빌라", "해뜨는 집", "아름드리빌"};
        String[] bank = {"하나은행", "기업은행", "신한은행", "국민은행", "우리은행", "농협은행"};
        String[] contents = {"", "여러분의 손길에 아이들이 너무 행복해합니다. 정말 감사드립니다.", "보육교사보조 자원봉사 모집합니다. 봉사시간 인정됩니다.", "많은 분들의 도움으로 아이들과 정말 행복한 소풍이 되었습니다. 감사합니다!!", "많은 관심 부탁드립니다."};
        String[] names = {"임장원", "임윤하", "임영웅", "김정국", "최은지", "정동원", "성시경", "조유진", "안예은", "이무진", "장원영", "이영지", "김민석", "강민경", "이찬원", "한동근", "김태연", "이청하", "이승윤", "홍승민", "정지민", "윤미래", "성효린", "송가인", "정승환", "이영현", "임윤아", "장민호", "최정훈", "황민현", "김다현", "김나영", "서인국", "박지원", "이석훈", "김채원", "양지은", "박효신", "김호중"};
        for (int i = 0; i < 107; i++) {
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SchoolDTO schoolDTO = new SchoolDTO();
            schoolDTO.setSchoolName(schoolNames[i % 22] + " 보육원");
            schoolDTO.setSchoolZipcode(String.format("%06d", random.nextInt(999999)));
            schoolDTO.setSchoolAddress(locations[i % 17] + random.nextInt(10));
            schoolDTO.setSchoolAddressDetail(addressDetail[random.nextInt(14)]);
            schoolDTO.setSchoolTeachers(random.nextInt(10) + 1);
            schoolDTO.setSchoolKids(random.nextInt(30) + 5);
            schoolDTO.setSchoolBudget((random.nextInt(50) + 1) * 10000000);
            schoolDTO.setSchoolBank(bank[random.nextInt(6)]);
            schoolDTO.setSchoolAccount(String.format("%014d", random.nextInt(100000000)));
            schoolDTO.setSchoolPhoneNumber("010" + String.format("%08d", random.nextInt(99999999)));
            schoolDTO.setSchoolQR("https://chart.googleapis.com/chart?cht=qr&chs=200x200&chl=http://localhost:9999/school/donation?userId=" + (101 + i));
            schoolDTO.setSchoolTitle("안녕하세요. " + schoolNames[i % 22] + "입니다.");
            schoolDTO.setSchoolContent("안녕하세요. " + schoolNames[i % 22] + "보육원입니다. " + contents[random.nextInt(5)]);
            schoolDTO.setUserEmail("asd" + i + "@asd.asd");
            schoolDTO.setUserName(names[random.nextInt(38)]);
            schoolDTO.setUserPassword("cXdlcjEyMzQh");
            if (i % 2 == 0) {
                schoolDTO.setUserPhoneNumber("010" + String.format("%08d", random.nextInt(99999999)));
            }
            schoolDTO.setUserProfile(null);
            schoolDTO.setDonationCount(0);
            schoolRepository.save(schoolDTO.toEntity());
        }
    }

    //    기부금 샘플 데이터
    @Test
    public void moneyTest() {
        for (int i = 0; i < 270; i++) {
            // 기부자 피기부자 샘플
            Long giver = Long.valueOf(random.nextInt(103) + 1);
            Long taker = random.nextInt(106) + 106L;

            MoneyDTO moneyDTO = new MoneyDTO();
            moneyDTO.setMoneyCash(random.nextInt((200)+1) * 1000L);
            moneyDTO.setUserId(taker);

            moneyService.payment(giver, moneyDTO);
        }
    }

    //    방문기부 샘플 데이터
    @Test
    public void visitTest() {
        for (int i = 0; i < 340; i++) {
            // 기부자 피기부자 샘플
            Long giver = Long.valueOf(random.nextInt(103) + 1);
            Long taker = random.nextInt(106) + 106L;

            ServiceDTO serviceDTO = new ServiceDTO();
            serviceDTO.setServiceVisitDate(LocalDateTime.of(2023,1,random.nextInt(31)+1,0,0,0));
            serviceDTO.setUserId(taker);

            serviceService.donationReservation(giver, serviceDTO);
        }
    }

//    //    재능기부 샘플
//    @Test
//    public void saveTalent() {
//        int donationCount = 0;
//        String[] talentTitle = {"저는 헬스 3년차 태릉인 입니다.", "피아노 선생님 입니다.", "이 바닥에 소문난 IT절대 고수 한동석 입니다.", "저는 국어 선생님 입니다.", "미술인생 20년 입니다."};
//        String[] talentContent = {"저는 헬스 3년차 태릉인 입니다. 제가 아이들에게 운동을 가르치겠습니다.", "아이들에게 피아노를 가르치고 싶어요.", "자바가 뭔지 차근 차근 가르칠게요.", "아이들에게 언어와 한국말의 아름다움을 전달 할게요.", "그림을 배우면 아이들이 좋아할 거예요"};
//        String[] category = {"운동", "음악", "IT", "교육", "미술"};
//        String[] location = {"서울", "경기도", "강원도", "충청도", "전라도", "경상도", "제주도"};
//
//        for (int i = 0; i < 132; i++) {
//            // 기부자 샘플
//            Long giver = Long.valueOf(random.nextInt(103) + 1);
//            // 재능기부 데이터
//            TalentDTO talentDTO = new TalentDTO();
//            talentDTO.setTalentTitle(talentTitle[i % 5]);
//            talentDTO.setTalentContent(talentContent[i % 5]);
//            talentDTO.setTalentAbleDate(LocalDateTime.of(2023, 2, random.nextInt(27), 0, 0));
//            talentDTO.setTalentCategory(category[i % 5]);
//            talentDTO.setTalentPlace(location[i % 7]);
//            talentDTO.setPeopleUserId(giver);
//
//            talentService.registerTalent(talentDTO);
//        }
//        for (int i = 0; i < 132; i++) {
//            if (i % 2 == 0) {
////              // 피기부자 샘플
//                Long taker = random.nextInt(106) + 106L;
//                TalentDTO talentDTO = new TalentDTO();
//                talentDTO.setSchoolUserId(taker);
//                talentService.signTalentPeople(talentDTO);
//                // 개인 총 기부 횟수 카운트
//                People people = peopleRepository.findById(giver).get();
//                donationCount = donationRepository.countByPeopleUserId(giver);
//                people.update(donationCount);
//                // 보육원 총 기부받은 횟수 카운트
//                School school = schoolRepository.findById(taker).get();
//                donationCount = donationRepository.countBySchoolUserId(taker);
//                school.update(donationCount);
//            }
//        }
//    }

    @Test
    public void test() {
        int test = random.nextInt(12);
        Long giver = Long.valueOf(random.nextInt(100));
        log.info("" + giver);
//        log.info(String.format("%014d",test));
    }
}
