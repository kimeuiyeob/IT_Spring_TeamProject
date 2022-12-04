package com.app.milestone.repository.reply;

import com.app.milestone.domain.LikeDTO;
import com.app.milestone.domain.ReplyDTO;
import com.app.milestone.entity.People;
import com.app.milestone.entity.Reply;
import com.app.milestone.entity.School;
import com.app.milestone.repository.PeopleRepository;
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
    private PeopleRepository peopleRepository;

    //    추가
    @Test
    public void saveTest() {
        School school = schoolRepository.findById(1L).get();
        People people = peopleRepository.findById(101L).get();
        ReplyDTO replyDTO = new ReplyDTO("안녕하세요 저는 댓글이예요", 1L, 101L);
        Reply reply = replyRepository.save(replyDTO.toEntity());
        reply.changeSchool(school);
        reply.changePeople(people);
    }

    //    조회
    @Test
    public void findAllTest() {
        replyRepository.findBySchoolId(1L).forEach(o -> log.info("보육원 : " + o.getSchoolUserId() + " 유저 : " + o.getPeopleUserId() + " 댓글 : " + o.getReplyContent()));
    }

    //    수정
    @Test
    public void updateTest() {
        replyRepository.findById(463L).get().update("저는 바뀐 댓글이예요");
    }

    //    삭제
    @Test
    public void deleteTest() {
        replyRepository.deleteById(463L);
    }

}
