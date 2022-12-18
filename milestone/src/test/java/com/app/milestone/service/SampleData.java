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
import java.util.List;
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
    private SchoolService schoolService;
    @Autowired
    private DonationRepository donationRepository;
    @Autowired
    private MoneyService moneyService;
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private TalentService talentService;
    @Autowired
    private TalentRepository talentRepository;
    @Autowired
    private NoticeRepository noticeRepository;
    @Autowired
    private WithdrawalRepository withdrawalRepository;


    private Random random = new Random();

    //    개인 샘플 데이터(105명)
    @Test
    public void savePeople() {
        String[] names = {"이지은", "이승철", "윤도현", "이문세", "정은지", "김광석", "김세정", "김경호", "유인나", "유해진", "마동석", "정우성", "이정재", "유재석", "이광수", "송지효", "김종국", "정호석", "전소민", "한동석", "김유정", "김태희", "김태희", "한가인", "전지현", "성은지", "정재훈", "문승희", "홍준성", "홍수현", "김인영", "배다빈", "박종우", "김민혁"};
//        log.info("==============================="+names.length);
        String[] nickNames = {"독수리", "흰개미", "고구마"};
        for (int i = 0; i < 105; i++) {
            try {
                Thread.sleep(500);
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
                Thread.sleep(500);
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
            schoolDTO.setUserPhoneNumber("010" + String.format("%08d", random.nextInt(99999999)));
            schoolDTO.setSchoolQR("https://chart.googleapis.com/chart?cht=qr&chs=200x200&chl=http://localhost:9999/school/donation?userId=" + (101 + i));
            schoolDTO.setSchoolTitle("안녕하세요. " + schoolNames[i % 22] + "입니다.");
            schoolDTO.setSchoolContent("안녕하세요. " + schoolNames[i % 22] + "보육원입니다. " + contents[random.nextInt(5)]);
            schoolDTO.setUserEmail("asd" + i + "@asd.asd");
            schoolDTO.setUserName(names[random.nextInt(38)]);
            schoolDTO.setUserPassword("cXdlcjEyMzQh");
            if (i % 2 == 0) {
                schoolDTO.setSchoolPhoneNumber("010" + String.format("%08d", random.nextInt(99999999)));
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
            Long giver = Long.valueOf(random.nextInt(103) + 2);
            Long taker = random.nextInt(106) + 106L;

            MoneyDTO moneyDTO = new MoneyDTO();
            moneyDTO.setMoneyCash(random.nextInt((200) + 1) * 1000L);
            moneyDTO.setUserId(taker);

            moneyService.payment(giver, moneyDTO);
        }
    }

    //    방문기부 샘플 데이터
    @Test
    public void visitTest() {
        for (int i = 0; i < 340; i++) {
            // 기부자 피기부자 샘플
            Long giver = Long.valueOf(random.nextInt(103) + 2);
            Long taker = random.nextInt(106) + 106L;

            ServiceDTO serviceDTO = new ServiceDTO();
            serviceDTO.setServiceVisitDate(LocalDateTime.of(2023, 1, random.nextInt(31) + 1, 0, 0, 0));
            serviceDTO.setUserId(taker);

            serviceService.donationReservation(giver, serviceDTO);
        }
    }

    //    재능기부 샘플
    @Test
    public void saveTalent() {
        String[] talentTitle = {"저는 헬스 3년차 태릉인 입니다.", "피아노 선생님 입니다.", "이 바닥에 소문난 IT절대 고수 한동석 입니다.", "저는 국어 선생님 입니다.", "미술인생 20년 입니다."};
        String[] talentContent = {"저는 헬스 3년차 태릉인 입니다. 제가 아이들에게 운동을 가르치겠습니다.", "아이들에게 피아노를 가르치고 싶어요.", "자바가 뭔지 차근 차근 가르칠게요.", "아이들에게 언어와 한국말의 아름다움을 전달 할게요.", "그림을 배우면 아이들이 좋아할 거예요"};
        String[] category = {"운동", "음악", "IT", "교육", "미술"};
        String[] location = {"서울", "경기도", "강원도", "충청도", "전라도", "경상도", "제주도"};
        Long giver = 0L;

        for (int i = 0; i < 132; i++) {
            // 기부자 샘플
            giver = Long.valueOf(random.nextInt(103) + 2);
            // 재능기부 데이터
            TalentDTO talentDTO = new TalentDTO();
            talentDTO.setTalentTitle(talentTitle[i % 5]);
            talentDTO.setTalentContent(talentContent[i % 5]);
            int dayOfMonth = random.nextInt(26) + 1;
            talentDTO.setTalentAbleDate(LocalDateTime.of(2023, 2, dayOfMonth, 0, 0));
            talentDTO.setTalentCategory(category[i % 5]);
            talentDTO.setTalentPlace(location[i % 7]);
            talentDTO.setPeopleUserId(giver);

            talentService.registerTalent(talentDTO);
        }

    }

    //    재능기부 신청
    @Test
    public void applyTalent() {
        int donationCount = 0;
        int i = 0;
        List<Talent> talents = talentRepository.findAll();
        for (Talent talent : talents) {
            i++;
            if (i % 2 == 0) {
                TalentDTO talentDTO = talentService.findByDonationId(talent.getDonationId());
//              // 피기부자
                Long takerId = random.nextInt(106) + 106L;
                // 피기부자 엔티티
                School school = schoolService.selectSchoolId(takerId);
                Talent talent1 = talentService.selectDonation(talentDTO);
                talent1.changeSchool(school);
                talentDTO.setSchoolUserId(takerId);

                talentService.signTalentPeople(talentDTO);
                // 개인 총 기부 횟수 카운트
                People people = peopleRepository.findById(talentDTO.getPeopleUserId()).get();
                donationCount = donationRepository.countByPeopleUserId(talent.getPeople().getUserId());
                people.update(donationCount);
                // 보육원 총 기부받은 횟수 카운트
                donationCount = donationRepository.countBySchoolUserId(takerId);
                school.update(donationCount);
            }
        }
    }

    //    공지사항 등록
    @Test
    public void saveNoticeTest() {
        String[] titles = {"[희망 나눔캠페인] 메타버스에서 놀며 기부체험하자!", "[나눔문화연구소] 제23회 비영리콜로키움 참가신청 안내","2023 마일스톤 모두다토론회 ＜청년, 모두의 자립＞ 함께해주세요", "2022 호우 피해 지원 특별모금 종료 안내", "[모집]배분사업 평가지원단 모집 공고","나눔지식네트워크 공동기획포럼 참가신청","사회복지공동모금회 배분명세 공고","기부 트렌드 컨퍼런스 안내 (+ 참가신청)","기부금영수증 발급 안내","아동문해력 사업 성과공유회 개최 안내","기부금품 사용완료 보고","[공지] 시스템 작업 공지 안내"};
        String[] contents = {"<p></p><p style=\"padding: 0px; margin: 0px; letter-spacing: -0.48px; box-sizing: border-box; color: rgb(83, 83, 83); font-size: 16px;\">새로운 기부문화의 장을 열기 위해 노력하는&nbsp;<span style=\"padding: 0px; margin: 0px; letter-spacing: -0.03em; box-sizing: border-box; color: rgb(255, 0, 0); font-weight: bold;\">마일스톤</span>에서는</p><p style=\"padding: 0px; margin: 0px; letter-spacing: -0.48px; : &quot;Noto Sans KR&quot;; box-sizing: border-box; color: rgb(83, 83, 83); font-size: 16px;\">희망2023나눔캠페인의 성공적 진행과 더 많은 사람들의 관심과 참여를 독려하기 위해</p><p style=\"padding: 0px; margin: 0px; letter-spacing: -0.48px; : &quot;Noto Sans KR&quot;; box-sizing: border-box; color: rgb(83, 83, 83); font-size: 16px;\">작년부터 메타버스 플랫폼 중 하나인 제페토에 메타버스 홍보관인 &lt;마일스톤X체리랜드&gt;를 운영 중에 있습니다.</p><p style=\"padding: 0px; margin: 0px; letter-spacing: -0.48px; : &quot;Noto Sans KR&quot;; box-sizing: border-box; color: rgb(83, 83, 83); font-size: 16px;\">&nbsp;</p><p style=\"padding: 0px; margin: 0px; letter-spacing: -0.48px; : &quot;Noto Sans KR&quot;; box-sizing: border-box; color: rgb(83, 83, 83); font-size: 16px;\">올해는 광화문광장을 배경으로 업데이트하여 운영중에 있고,&nbsp;<span style=\"padding: 0px; margin: 0px; letter-spacing: -0.02em; box-sizing: border-box;\">더 많은 사람들의 참여와 관심을 독려하기 위해 이벤트를 진행합니다.</span></p><p style=\"padding: 0px; margin: 0px; letter-spacing: -0.48px; : &quot;Noto Sans KR&quot;; box-sizing: border-box; color: rgb(83, 83, 83); font-size: 16px;\">많은 관심과 참여부탁드립니다. ^^&nbsp;</p><p></p>",
                "<p></p><p style=\"padding: 0px; margin: 0px; letter-spacing: -0.48px; box-sizing: border-box; color: rgb(83, 83, 83); font-size: 16px;\">안녕하세요.</p><p style=\"padding: 0px; margin: 0px; letter-spacing: -0.48px; : &quot;Noto Sans KR&quot;; box-sizing: border-box; color: rgb(83, 83, 83); font-size: 16px;\">마일스톤 나눔문화연구소와 아름다운재단 기부문화연구소가 함께하는 제24회 비영리콜로키움에 여러분들을 초대합니다.</p><p style=\"padding: 0px; margin: 0px; letter-spacing: -0.48px; : &quot;Noto Sans KR&quot;; box-sizing: border-box; color: rgb(83, 83, 83); font-size: 16px;\"><br style=\"padding: 0px; margin: 0px; letter-spacing: -0.03em; box-sizing: border-box;\"></p><p style=\"padding: 0px; margin: 0px; letter-spacing: -0.48px; : &quot;Noto Sans KR&quot;; box-sizing: border-box; color: rgb(83, 83, 83); font-size: 16px;\">많은 관심 부탁드립니다.</p><p style=\"padding: 0px; margin: 0px; letter-spacing: -0.48px; : &quot;Noto Sans KR&quot;; box-sizing: border-box; color: rgb(83, 83, 83); font-size: 16px;\"><br style=\"padding: 0px; margin: 0px; letter-spacing: -0.03em; box-sizing: border-box;\"></p><p style=\"padding: 0px; margin: 0px; letter-spacing: -0.48px; : &quot;Noto Sans KR&quot;; box-sizing: border-box; color: rgb(83, 83, 83); font-size: 16px;\">- 행사 일시 : 2022.12.09.(금) 15:00 ~ 17:30</p><p style=\"padding: 0px; margin: 0px; letter-spacing: -0.48px; : &quot;Noto Sans KR&quot;; box-sizing: border-box; color: rgb(83, 83, 83); font-size: 16px;\">- 행사 장소 : 사회복지공동모금회 지하 1층 대강당 (서울시 중구 세종대로 21길 39)</p><p style=\"padding: 0px; margin: 0px; letter-spacing: -0.48px; : &quot;Noto Sans KR&quot;; box-sizing: border-box; color: rgb(83, 83, 83); font-size: 16px;\">- 신청 기간 : 2022.11.23.(수) ~ 2022.12.07.(수)</p><p style=\"padding: 0px; margin: 0px; letter-spacing: -0.48px; : &quot;Noto Sans KR&quot;; box-sizing: border-box; color: rgb(83, 83, 83); font-size: 16px;\">- 참가신청링크 :&nbsp;<a href=\"https://forms.gle/tPZdQGFmcKk8XN7s7\" target=\"_blank\" style=\"padding: 0px; margin: 0px; letter-spacing: -0.03em; : &quot;Noto Sans KR&quot;; box-sizing: border-box; color: rgb(85, 85, 85); outline-offset: 1px; text-decoration: none;\">https://forms.gle/tPZdQGFmcKk8XN7s7</a></p><p></p>",
                "<p></p><p style=\"padding: 0px; margin: 0px; letter-spacing: -0.48px; box-sizing: border-box; font-size: 16px;\"><span style=\"color: rgb(83, 83, 83); : &quot;Apple SD 산돌고딕 Neo&quot;; font-size: 18.4409px; letter-spacing: -0.553227px;\">사회복지공동모금회에서는 2023년 MZ와 함께한 나눔 컨퍼런스를 통해 청년 기부문화에 대해 알아보고, 이해하는 소통의 시간을 가졌습니다.</span><br style=\"padding: 0px; margin: 0px; letter-spacing: -0.553227px; box-sizing: border-box; font-size: 18.4409px;\"><br style=\"padding: 0px; margin: 0px; letter-spacing: -0.553227px; box-sizing: border-box; font-size: 18.4409px;\"><span style=\": &quot;Apple SD 산돌고딕 Neo&quot;; font-size: 18.4409px; letter-spacing: -0.553227px;\"><font color=\"#535353\">2023년에도 '</font><span style=\"background-color: rgb(255, 231, 206);\">청년</span><font color=\"#535353\">' 에 주목하여 마일스톤에서 진행한 다양한 유형의 청년 지원 사례를 공유하고 토론을 통해 '청년 자립'에 대한 솔루션을 모색하고자 2022 모두다토론회 &lt;청년, 모두의 자립&gt;을 주제로 아래와 같이 진행하고자 합니다.</font></span><br style=\"padding: 0px; margin: 0px; letter-spacing: -0.553227px; box-sizing: border-box; font-size: 18.4409px;\"><br style=\"padding: 0px; margin: 0px; letter-spacing: -0.553227px; box-sizing: border-box; font-size: 18.4409px;\"><span style=\"color: rgb(83, 83, 83); : &quot;Apple SD 산돌고딕 Neo&quot;; font-size: 18.4409px; letter-spacing: -0.553227px;\">청년들의 자유로운 의견을 수렴하고, 솔루션을 모색하는 본 토론회는 엠보팅(모바일 참여형 투표)으로 진행되오니 부담 없이 참석하여 다양한 의견 부탁드립니다.</span><br style=\"padding: 0px; margin: 0px; letter-spacing: -0.553227px; box-sizing: border-box; font-size: 18.4409px;\"><br style=\"padding: 0px; margin: 0px; letter-spacing: -0.553227px; box-sizing: border-box; font-size: 18.4409px;\"><span style=\"color: rgb(83, 83, 83); : &quot;Apple SD 산돌고딕 Neo&quot;; font-size: 18.4409px; letter-spacing: -0.553227px;\">감사합니다!</span><br></p><p></p>",
                "<p></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word; color: rgb(51, 51, 51); : noto; letter-spacing: -1px;\"><span style=\"font-size: 16px;\"><span>시스템 점검 작업으로 인하여 마일스톤 어린이재단 홈페이지&nbsp;</span></span></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word; color: rgb(51, 51, 51); : noto; letter-spacing: -1px;\"><span style=\"font-size: 16px;\"><span style=\": &quot;맑은 고딕&quot;;\">이용이 불안정 할 수 있으니 이용에 참고 하시기 바랍니다.&nbsp;</span></span></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word; color: rgb(51, 51, 51); : noto; letter-spacing: -1px;\">&nbsp;</p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word; color: rgb(51, 51, 51); : noto; letter-spacing: -1px;\"><span style=\"font-size: 16px;\"><span style=\": &quot;맑은 고딕&quot;;\"><strong>&nbsp; &nbsp; &nbsp; &nbsp; -작업사유: 시스템 점검&nbsp;</strong></span></span></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word; color: rgb(51, 51, 51); : noto; letter-spacing: -1px;\"><span style=\"font-size: 16px;\"><span style=\": &quot;맑은 고딕&quot;;\"><strong>&nbsp; &nbsp; &nbsp; &nbsp; -작업일시: 2021년 9월 11일(토) 00:00 ~ 03:00&nbsp;</strong></span></span></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word; color: rgb(51, 51, 51); : noto; letter-spacing: -1px;\">&nbsp;</p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word; color: rgb(51, 51, 51); : noto; letter-spacing: -1px;\"><span style=\"font-size: 16px;\"><span style=\": &quot;맑은 고딕&quot;;\">※ 진행 상황에 따라 작업시간은 단축 또는 연장될 수 있습니다.&nbsp;</span></span></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word; color: rgb(51, 51, 51); : noto; letter-spacing: -1px;\"><span style=\"font-size: 16px;\"><span style=\">&nbsp; &nbsp;이용에 불편을 드려 죄송합니다.&nbsp;</span></span></p><p></p>",
                "<p></p><p style=\"text-align: left; margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word; color: rgb(51, 51, 51); : noto; letter-spacing: -1px;\"><span style=\": HY신명조; font-size: 16px;\">안녕하세요, 마일스톤입니다.</span></p><p style=\"text-align: left; margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word; color: rgb(51, 51, 51); : noto; letter-spacing: -1px;\"><span style=\": HY신명조; font-size: 16px;\">하기와 같이 기부금품 사용완료 보고 전달드립니다.</span></p><p style=\"text-align: left; margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word; color: rgb(51, 51, 51); : noto; letter-spacing: -1px;\"><span style=\": HY신명조; font-size: 16px;\"><br></span></p><p style=\"text-align: left; margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word; color: rgb(51, 51, 51); : noto; letter-spacing: -1px;\"><span style=\": HY신명조; font-size: 16px;\">1. 모 집 자: 사회복지법인 어린이재단</span><br></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word; color: rgb(51, 51, 51); : noto; letter-spacing: -1px;\"><span style=\"font-size: 16px;\"><span style=\"line-height: 28.8px;\"><span style=\": HY신명조;\">2. 모집목적: 국내외 빈곤, 장애 아동의 생계비, 주거비, 의료비 등의 지원 및 긴급지원</span></span></span></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word; color: rgb(51, 51, 51); : noto; letter-spacing: -1px;\"><span style=\"font-size: 16px;\"><span style=\"line-height: 28.8px;\"><span style=\": HY신명조;\">3. 모집기간: 2022.01.01. ~ 2022.12.31. (제2022-38호)</span></span></span></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word; color: rgb(51, 51, 51); : noto; letter-spacing: -1px;\"><span style=\"font-size: 16px;\"><span style=\"line-height: 28.8px;\"><span style=\": HY신명조;\">4. 모집등록금액 : 2,800,000,000원</span></span></span></p><p></p>",
                "<p></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word;\"><span style=\"color: rgb(51, 51, 51);/* : HY신명조; */font-size: 16px;letter-spacing: -1px;\">□ 일시 : 2023년 10월 18일(금) 오후 5시~6시</span><br></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word;\"><font color=\"#333333\" face=\"HY신명조\"><span style=\"font-size: 16px; letter-spacing: -1px;\">□ 참여대상 : 누구나 참여 가능</span></font></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word;\"><font face=\"HY신명조\"><span style=\"font-size: 16px; letter-spacing: -1px;\"><font color=\"#333333\">□ 참여방법 : 유튜브 공식 채널을 통한 실시간 스트리밍 </font><font color=\"#0000ff\">링크 바로가기</font><font color=\"#333333\"> (※ 시상식 종료 후, 영상 미공개 처리예정)</font></span></font></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word;\"><font color=\"#333333\" face=\"HY신명조\"><span style=\"font-size: 16px; letter-spacing: -1px;\">&nbsp;</span></font></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word;\"><font color=\"#333333\" face=\"HY신명조\"><span style=\"font-size: 16px; letter-spacing: -1px;\">마일스톤 나눔공모전 시상식이 있습니다.</span></font></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word;\"><font color=\"#333333\" face=\"HY신명조\"><span style=\"font-size: 16px; letter-spacing: -1px;\">축하 메시지로 찾아온 깜짝 손님이 있으니,</span></font></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word;\"><font color=\"#333333\" face=\"HY신명조\"><span style=\"font-size: 16px; letter-spacing: -1px;\">유튜브 스트리밍을 통해 함께 해주시길 바랍니다.</span></font></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word;\"><font color=\"#333333\" face=\"HY신명조\"><span style=\"font-size: 16px; letter-spacing: -1px;\">&nbsp;</span></font></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word;\"><font color=\"#333333\" face=\"HY신명조\"><span style=\"font-size: 16px; letter-spacing: -1px;\">수상하신 모든 분들 진심으로 축하드립니다.</span></font></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word;\"><font color=\"#333333\" face=\"HY신명조\"><span style=\"font-size: 16px; letter-spacing: -1px;\">&nbsp;</span></font></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word;\"><font color=\"#333333\" face=\"HY신명조\"><span style=\"font-size: 16px; letter-spacing: -1px;\">감사합니다.</span></font></p><p></p>"

        };


        for (int i = 0; i < 30; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            NoticeDTO noticeDTO = new NoticeDTO();
            noticeDTO.setNoticeTitle(titles[i % 12]);
            noticeDTO.setNoticeContent(contents[i % 3]);

            noticeRepository.save(noticeDTO.toEntity());
        }
    }

    //    회원탈퇴 저장
    @Test
    public void saveWithdrawalTest() {
//        WithdrawalDTO withdrawalDTO = new WithdrawalDTO();
//        withdrawalDTO.setWithdrawalReason("대체할 만한 서비스를 찾았어요");
//        withdrawalDTO.setWithdrawalUserType("보육원");                   //사용자 형태
//        withdrawalRepository.save(withdrawalDTO.toEntity());

        String[] reasons = {"이용하고 싶은 서비스가 없어요", "서비스 퀄리티가 낮아요", "비매너 회원을 만났어요", "잦은 오류가 발생해요", "대체할 만한 서비스를 찾았어요"};
        String[] types = {"보육원", "일반"};
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            WithdrawalDTO withdrawalDTO = new WithdrawalDTO();
            withdrawalDTO.setWithdrawalReason(reasons[i % 5]);
            withdrawalDTO.setWithdrawalUserType(types[i % 2]);

            withdrawalRepository.save(withdrawalDTO.toEntity());
        }
    }

}
