package com.app.milestone.notice;

import com.app.milestone.domain.NoticeDTO;
import com.app.milestone.domain.PeopleDTO;
import com.app.milestone.entity.Notice;
import com.app.milestone.entity.People;
import com.app.milestone.repository.NoticeRepository;
import com.app.milestone.repository.PeopleRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class NoticeTest {
    @Autowired
    private NoticeRepository noticeRepository;

    private JPAQueryFactory jpaQueryFactory;

//    @Test
//    public void saveTest() {
//        NoticeDTO noticeDTO = new NoticeDTO("제목", "내용");
//        noticeRepository.save(noticeDTO.toEntity());
//    }
//
//    @Test
//    public void updateTest() {
//        Notice notice = noticeRepository.findById(2L).get();
//
//        notice.update("수정 제목", "수정 내용");
//    }
//
//    @Test
//    public void findTest() {
//        assertThat(noticeRepository.findById(2L).get().getNoticeTitle()).isEqualTo("수정 제목");
//    }
//
//    @Test
//    public void deleteTest() {
//        noticeRepository.deleteById(2L);
//    }
}
