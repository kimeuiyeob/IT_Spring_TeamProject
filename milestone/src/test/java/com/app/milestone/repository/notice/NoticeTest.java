package com.app.milestone.repository.notice;

import com.app.milestone.domain.NoticeDTO;
import com.app.milestone.entity.Notice;
import com.app.milestone.repository.NoticeRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
        NoticeDTO noticeDTO = new NoticeDTO("[점검]긴급 점검입니다.", "내용");
        noticeRepository.save(noticeDTO.toEntity());
    }

//    더미데이터, 페이징
    @Test
    public void queryDslTest() {
        for (int i = 0; i < 50; i++) {
            NoticeDTO noticeDTO = new NoticeDTO("[점검]긴급 점검입니다.", "내용 : "+i);
            Notice notice = noticeDTO.toEntity();
            noticeRepository.save(notice);
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

//    공지사항 전체조회(오름차순)
    @Test
    public void findAllTest() {
        jpaQueryFactory.select(notice).from(notice).fetch().forEach(notice -> log.info("공지사항 : "+notice.getNoticeTitle()));
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
