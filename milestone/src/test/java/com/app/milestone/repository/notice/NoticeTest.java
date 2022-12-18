package com.app.milestone.repository.notice;

import com.app.milestone.domain.NoticeDTO;
import com.app.milestone.domain.PeopleDTO;
import com.app.milestone.entity.Notice;
import com.app.milestone.repository.NoticeRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static com.app.milestone.entity.QNotice.notice;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class NoticeTest {
    @Autowired
    private NoticeRepository noticeRepository;
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

//    공지사항 등록
    @Test
    public void saveTest() {
        String[] titles = {"[희망 나눔캠페인] 메타버스에서 놀며 기부체험하자!", "[나눔문화연구소] 제23회 비영리콜로키움 참가신청 안내","2023 마일스톤 모두다토론회 ＜청년, 모두의 자립＞ 함께해주세요", "2022 호우 피해 지원 특별모금 종료 안내", "[모집]배분사업 평가지원단 모집 공고","나눔지식네트워크 공동기획포럼 참가신청","사회복지공동모금회 배분명세 공고","기부 트렌드 컨퍼런스 안내 (+ 참가신청)","기부금영수증 발급 안내","아동문해력 사업 성과공유회 개최 안내","기부금품 사용완료 보고","[공지] 시스템 작업 공지 안내"};
        String[] contents = {"<p></p><p style=\"padding: 0px; margin: 0px; letter-spacing: -0.48px; box-sizing: border-box; color: rgb(83, 83, 83); font-size: 16px;\">새로운 기부문화의 장을 열기 위해 노력하는&nbsp;<span style=\"padding: 0px; margin: 0px; letter-spacing: -0.03em; box-sizing: border-box; color: rgb(255, 0, 0); font-weight: bold;\">마일스톤</span>에서는</p><p style=\"padding: 0px; margin: 0px; letter-spacing: -0.48px; : &quot;Noto Sans KR&quot;; box-sizing: border-box; color: rgb(83, 83, 83); font-size: 16px;\">희망2023나눔캠페인의 성공적 진행과 더 많은 사람들의 관심과 참여를 독려하기 위해</p><p style=\"padding: 0px; margin: 0px; letter-spacing: -0.48px; : &quot;Noto Sans KR&quot;; box-sizing: border-box; color: rgb(83, 83, 83); font-size: 16px;\">작년부터 메타버스 플랫폼 중 하나인 제페토에 메타버스 홍보관인 &lt;마일스톤X체리랜드&gt;를 운영 중에 있습니다.</p><p style=\"padding: 0px; margin: 0px; letter-spacing: -0.48px; : &quot;Noto Sans KR&quot;; box-sizing: border-box; color: rgb(83, 83, 83); font-size: 16px;\">&nbsp;</p><p style=\"padding: 0px; margin: 0px; letter-spacing: -0.48px; : &quot;Noto Sans KR&quot;; box-sizing: border-box; color: rgb(83, 83, 83); font-size: 16px;\">올해는 광화문광장을 배경으로 업데이트하여 운영중에 있고,&nbsp;<span style=\"padding: 0px; margin: 0px; letter-spacing: -0.02em; box-sizing: border-box;\">더 많은 사람들의 참여와 관심을 독려하기 위해 이벤트를 진행합니다.</span></p><p style=\"padding: 0px; margin: 0px; letter-spacing: -0.48px; : &quot;Noto Sans KR&quot;; box-sizing: border-box; color: rgb(83, 83, 83); font-size: 16px;\">많은 관심과 참여부탁드립니다. ^^&nbsp;</p><p></p>",
                "<p></p><p style=\"padding: 0px; margin: 0px; letter-spacing: -0.48px; box-sizing: border-box; color: rgb(83, 83, 83); font-size: 16px;\">안녕하세요.</p><p style=\"padding: 0px; margin: 0px; letter-spacing: -0.48px; : &quot;Noto Sans KR&quot;; box-sizing: border-box; color: rgb(83, 83, 83); font-size: 16px;\">마일스톤 나눔문화연구소와 아름다운재단 기부문화연구소가 함께하는 제24회 비영리콜로키움에 여러분들을 초대합니다.</p><p style=\"padding: 0px; margin: 0px; letter-spacing: -0.48px; : &quot;Noto Sans KR&quot;; box-sizing: border-box; color: rgb(83, 83, 83); font-size: 16px;\"><br style=\"padding: 0px; margin: 0px; letter-spacing: -0.03em; box-sizing: border-box;\"></p><p style=\"padding: 0px; margin: 0px; letter-spacing: -0.48px; : &quot;Noto Sans KR&quot;; box-sizing: border-box; color: rgb(83, 83, 83); font-size: 16px;\">많은 관심 부탁드립니다.</p><p style=\"padding: 0px; margin: 0px; letter-spacing: -0.48px; : &quot;Noto Sans KR&quot;; box-sizing: border-box; color: rgb(83, 83, 83); font-size: 16px;\"><br style=\"padding: 0px; margin: 0px; letter-spacing: -0.03em; box-sizing: border-box;\"></p><p style=\"padding: 0px; margin: 0px; letter-spacing: -0.48px; : &quot;Noto Sans KR&quot;; box-sizing: border-box; color: rgb(83, 83, 83); font-size: 16px;\">- 행사 일시 : 2022.12.09.(금) 15:00 ~ 17:30</p><p style=\"padding: 0px; margin: 0px; letter-spacing: -0.48px; : &quot;Noto Sans KR&quot;; box-sizing: border-box; color: rgb(83, 83, 83); font-size: 16px;\">- 행사 장소 : 사회복지공동모금회 지하 1층 대강당 (서울시 중구 세종대로 21길 39)</p><p style=\"padding: 0px; margin: 0px; letter-spacing: -0.48px; : &quot;Noto Sans KR&quot;; box-sizing: border-box; color: rgb(83, 83, 83); font-size: 16px;\">- 신청 기간 : 2022.11.23.(수) ~ 2022.12.07.(수)</p><p style=\"padding: 0px; margin: 0px; letter-spacing: -0.48px; : &quot;Noto Sans KR&quot;; box-sizing: border-box; color: rgb(83, 83, 83); font-size: 16px;\">- 참가신청링크 :&nbsp;<a href=\"https://forms.gle/tPZdQGFmcKk8XN7s7\" target=\"_blank\" style=\"padding: 0px; margin: 0px; letter-spacing: -0.03em; : &quot;Noto Sans KR&quot;; box-sizing: border-box; color: rgb(85, 85, 85); outline-offset: 1px; text-decoration: none;\">https://forms.gle/tPZdQGFmcKk8XN7s7</a></p><p></p>",
                "<p></p><p style=\"padding: 0px; margin: 0px; letter-spacing: -0.48px; box-sizing: border-box; font-size: 16px;\"><span style=\"color: rgb(83, 83, 83); : &quot;Apple SD 산돌고딕 Neo&quot;; font-size: 18.4409px; letter-spacing: -0.553227px;\">사회복지공동모금회에서는 2023년 MZ와 함께한 나눔 컨퍼런스를 통해 청년 기부문화에 대해 알아보고, 이해하는 소통의 시간을 가졌습니다.</span><br style=\"padding: 0px; margin: 0px; letter-spacing: -0.553227px; box-sizing: border-box; font-size: 18.4409px;\"><br style=\"padding: 0px; margin: 0px; letter-spacing: -0.553227px; box-sizing: border-box; font-size: 18.4409px;\"><span style=\": &quot;Apple SD 산돌고딕 Neo&quot;; font-size: 18.4409px; letter-spacing: -0.553227px;\"><font color=\"#535353\">2023년에도 '</font><span style=\"background-color: rgb(255, 231, 206);\">청년</span><font color=\"#535353\">' 에 주목하여 마일스톤에서 진행한 다양한 유형의 청년 지원 사례를 공유하고 토론을 통해 '청년 자립'에 대한 솔루션을 모색하고자 2022 모두다토론회 &lt;청년, 모두의 자립&gt;을 주제로 아래와 같이 진행하고자 합니다.</font></span><br style=\"padding: 0px; margin: 0px; letter-spacing: -0.553227px; box-sizing: border-box; font-size: 18.4409px;\"><br style=\"padding: 0px; margin: 0px; letter-spacing: -0.553227px; box-sizing: border-box; font-size: 18.4409px;\"><span style=\"color: rgb(83, 83, 83); : &quot;Apple SD 산돌고딕 Neo&quot;; font-size: 18.4409px; letter-spacing: -0.553227px;\">청년들의 자유로운 의견을 수렴하고, 솔루션을 모색하는 본 토론회는 엠보팅(모바일 참여형 투표)으로 진행되오니 부담 없이 참석하여 다양한 의견 부탁드립니다.</span><br style=\"padding: 0px; margin: 0px; letter-spacing: -0.553227px; box-sizing: border-box; font-size: 18.4409px;\"><br style=\"padding: 0px; margin: 0px; letter-spacing: -0.553227px; box-sizing: border-box; font-size: 18.4409px;\"><span style=\"color: rgb(83, 83, 83); : &quot;Apple SD 산돌고딕 Neo&quot;; font-size: 18.4409px; letter-spacing: -0.553227px;\">감사합니다!</span><br></p><p></p>",
                "<p></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word; color: rgb(51, 51, 51); : noto; letter-spacing: -1px;\"><span style=\"font-size: 16px;\"><span>시스템 점검 작업으로 인하여 마일스톤 어린이재단 홈페이지&nbsp;</span></span></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word; color: rgb(51, 51, 51); : noto; letter-spacing: -1px;\"><span style=\"font-size: 16px;\"><span style=\": &quot;맑은 고딕&quot;;\">이용이 불안정 할 수 있으니 이용에 참고 하시기 바랍니다.&nbsp;</span></span></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word; color: rgb(51, 51, 51); : noto; letter-spacing: -1px;\">&nbsp;</p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word; color: rgb(51, 51, 51); : noto; letter-spacing: -1px;\"><span style=\"font-size: 16px;\"><span style=\": &quot;맑은 고딕&quot;;\"><strong>&nbsp; &nbsp; &nbsp; &nbsp; -작업사유: 시스템 점검&nbsp;</strong></span></span></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word; color: rgb(51, 51, 51); : noto; letter-spacing: -1px;\"><span style=\"font-size: 16px;\"><span style=\": &quot;맑은 고딕&quot;;\"><strong>&nbsp; &nbsp; &nbsp; &nbsp; -작업일시: 2021년 9월 11일(토) 00:00 ~ 03:00&nbsp;</strong></span></span></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word; color: rgb(51, 51, 51); : noto; letter-spacing: -1px;\">&nbsp;</p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word; color: rgb(51, 51, 51); : noto; letter-spacing: -1px;\"><span style=\"font-size: 16px;\"><span style=\": &quot;맑은 고딕&quot;;\">※ 진행 상황에 따라 작업시간은 단축 또는 연장될 수 있습니다.&nbsp;</span></span></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word; color: rgb(51, 51, 51); : noto; letter-spacing: -1px;\"><span style=\"font-size: 16px;\"><span style=\">&nbsp; &nbsp;이용에 불편을 드려 죄송합니다.&nbsp;</span></span></p><p></p>",
                "<p></p><p style=\"text-align: left; margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word; color: rgb(51, 51, 51); : noto; letter-spacing: -1px;\"><span style=\": HY신명조; font-size: 16px;\">안녕하세요, 마일스톤입니다.</span></p><p style=\"text-align: left; margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word; color: rgb(51, 51, 51); : noto; letter-spacing: -1px;\"><span style=\": HY신명조; font-size: 16px;\">하기와 같이 기부금품 사용완료 보고 전달드립니다.</span></p><p style=\"text-align: left; margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word; color: rgb(51, 51, 51); : noto; letter-spacing: -1px;\"><span style=\": HY신명조; font-size: 16px;\"><br></span></p><p style=\"text-align: left; margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word; color: rgb(51, 51, 51); : noto; letter-spacing: -1px;\"><span style=\": HY신명조; font-size: 16px;\">1. 모 집 자: 사회복지법인 어린이재단</span><br></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word; color: rgb(51, 51, 51); : noto; letter-spacing: -1px;\"><span style=\"font-size: 16px;\"><span style=\"line-height: 28.8px;\"><span style=\": HY신명조;\">2. 모집목적: 국내외 빈곤, 장애 아동의 생계비, 주거비, 의료비 등의 지원 및 긴급지원</span></span></span></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word; color: rgb(51, 51, 51); : noto; letter-spacing: -1px;\"><span style=\"font-size: 16px;\"><span style=\"line-height: 28.8px;\"><span style=\": HY신명조;\">3. 모집기간: 2022.01.01. ~ 2022.12.31. (제2022-38호)</span></span></span></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word; color: rgb(51, 51, 51); : noto; letter-spacing: -1px;\"><span style=\"font-size: 16px;\"><span style=\"line-height: 28.8px;\"><span style=\": HY신명조;\">4. 모집등록금액 : 2,800,000,000원</span></span></span></p><p></p>",
                "<p></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word;\"><span style=\"color: rgb(51, 51, 51);/* : HY신명조; */font-size: 16px;letter-spacing: -1px;\">□ 일시 : 2023년 10월 18일(금) 오후 5시~6시</span><br></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word;\"><font color=\"#333333\" face=\"HY신명조\"><span style=\"font-size: 16px; letter-spacing: -1px;\">□ 참여대상 : 누구나 참여 가능</span></font></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word;\"><font face=\"HY신명조\"><span style=\"font-size: 16px; letter-spacing: -1px;\"><font color=\"#333333\">□ 참여방법 : 유튜브 공식 채널을 통한 실시간 스트리밍 </font><font color=\"#0000ff\">링크 바로가기</font><font color=\"#333333\"> (※ 시상식 종료 후, 영상 미공개 처리예정)</font></span></font></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word;\"><font color=\"#333333\" face=\"HY신명조\"><span style=\"font-size: 16px; letter-spacing: -1px;\">&nbsp;</span></font></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word;\"><font color=\"#333333\" face=\"HY신명조\"><span style=\"font-size: 16px; letter-spacing: -1px;\">마일스톤 나눔공모전 시상식이 있습니다.</span></font></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word;\"><font color=\"#333333\" face=\"HY신명조\"><span style=\"font-size: 16px; letter-spacing: -1px;\">축하 메시지로 찾아온 깜짝 손님이 있으니,</span></font></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word;\"><font color=\"#333333\" face=\"HY신명조\"><span style=\"font-size: 16px; letter-spacing: -1px;\">유튜브 스트리밍을 통해 함께 해주시길 바랍니다.</span></font></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word;\"><font color=\"#333333\" face=\"HY신명조\"><span style=\"font-size: 16px; letter-spacing: -1px;\">&nbsp;</span></font></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word;\"><font color=\"#333333\" face=\"HY신명조\"><span style=\"font-size: 16px; letter-spacing: -1px;\">수상하신 모든 분들 진심으로 축하드립니다.</span></font></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word;\"><font color=\"#333333\" face=\"HY신명조\"><span style=\"font-size: 16px; letter-spacing: -1px;\">&nbsp;</span></font></p><p style=\"margin: 0px 0px 10px; padding: 0px; outline: none; width: 1140px; overflow-wrap: break-word;\"><font color=\"#333333\" face=\"HY신명조\"><span style=\"font-size: 16px; letter-spacing: -1px;\">감사합니다.</span></font></p><p></p>"

        };


        for (int i = 0; i < 50; i++) {
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

//    공지사항 수정
    @Test
    public void updateTest() {
//        Notice notice = noticeRepository.findById(1L).get();
        NoticeDTO noticeDTO = new NoticeDTO();
        Notice notice = noticeDTO.toEntity();

        notice.update("[공지]공지로 수정합니다.", "수정내용");
    }

//    공지사항 전체조회
    @Test
    public void getListTest() {
//        jpaQueryFactory.select(notice).from(notice).fetch().forEach(notice -> log.info("공지사항 : "+notice.getNoticeTitle()));
    }

//    공지사항 검색하여 최신순으로 조회
    @Test
    public void findTest() {
//        assertThat(noticeRepository.findById(1L).get().getNoticeTitle()).isEqualTo("[점검]긴급 점검입니다.");
        jpaQueryFactory.selectFrom(notice)
                .where(notice.noticeTitle.contains("긴급"))
                .orderBy(notice.createdDate.desc())
                .fetch().forEach(notice->log.info("긴급 검색어 포함? "+notice.getNoticeContent()+
                                                "\n생성날짜? "+notice.getCreatedDate()));
    }

//    공지사항 삭제
    @Test
    public void deleteTest() {
        noticeRepository.deleteById(1L);
    }
}
