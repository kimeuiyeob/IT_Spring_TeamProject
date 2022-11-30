package com.app.milestone.notice;

import com.app.milestone.domain.NoticeDTO;
import com.app.milestone.domain.PeopleDTO;
import com.app.milestone.entity.Notice;
import com.app.milestone.entity.People;
import com.app.milestone.entity.QNotice;
import com.app.milestone.repository.NoticeRepository;
import com.app.milestone.repository.PeopleRepository;
import com.app.milestone.type.Maintenance;
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

    @Test
    public void saveTest() {
        NoticeDTO noticeDTO = new NoticeDTO("[점검]긴급 점검입니다.", "내용", Maintenance.점검);
        noticeRepository.save(noticeDTO.toEntity());
    }

    @Test
    public void updateTest() {
        Notice notice = noticeRepository.findById(1L).get();

        notice.update("[공지]공지로 수정합니다.", "수정내용", Maintenance.공지);
    }

    @Test
    public void findTest() {
        assertThat(noticeRepository.findById(1L).get().getNoticeContent()).isEqualTo("수정내용");
        jpaQueryFactory.selectFrom(notice)
                .where(notice.noticeContent.eq("수정내용"))
                .limit(1)
                .fetch().forEach(notice->log.info("공지사항 내용 : "+notice.getNoticeContent()));
    }

    @Test
    public void deleteTest() {
        noticeRepository.deleteById(1L);
    }
}
