package com.app.milestone.reply;

import com.app.milestone.domain.ReplyDTO;
import com.app.milestone.entity.Reply;
import com.app.milestone.entity.School;
import com.app.milestone.repository.ReplyRepository;
import com.app.milestone.repository.SchoolRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.app.milestone.entity.QReply.reply;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class ReplyTest {
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    //    보육원에 댓글 달기
    @Test
    public void saveTest() {
        School schoolId = schoolRepository.findById(11L).get();
        ReplyDTO replyDTO = new ReplyDTO();
        replyDTO.setReplyContent("새로운 댓글이에용");

        Reply reply = replyDTO.toEntity();
        replyRepository.save(reply);
        reply.changeSchool(schoolId);
    }

    //    보육원에 달린 댓글 조회
    @Test
    public void findByUserIdTest() {
        jpaQueryFactory
                .select(reply)
                .from(reply)
                .where(reply.school.userId.eq(11L))
                .fetch()
                .forEach(reply -> log.info("댓글 : " + reply.getReplyContent()));
    }

    //    총 댓글 수 조회
    @Test
    public void countTest() {
        List<Long> replyCount = jpaQueryFactory
                .select(reply.count())
                .from(reply)
                .where(reply.school.userId.eq(11L))
                .fetch();

        log.info("댓글 수 : " + replyCount.get(0));
    }

    //    댓글 수정
    @Test
    public void updateTest() {
        //asserThat 오류가 났을 때 친절히 알려줌~~!!!
        Reply reply = replyRepository.findById(14L).get();
        reply.update("댓글 수정 합니다");
    }

    //    댓글 삭제
    @Test
    public void deleteTest() {
        replyRepository.deleteById(12L);
    }
}
