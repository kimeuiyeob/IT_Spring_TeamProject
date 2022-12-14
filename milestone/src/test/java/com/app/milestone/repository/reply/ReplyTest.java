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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
//        화면에서 받음
        Long schoolId = 5L;
        Long peopleId = 132L;
        String content = "안녕하세요 저는 댓글이예요";

        for (int i = 0; i < 33; i++) {
            School school = schoolRepository.findById(schoolId).get();
            People people = peopleRepository.findById(peopleId).get();
            ReplyDTO replyDTO = new ReplyDTO();
            replyDTO.setReplyContent(content+i);
            replyDTO.setSchoolUserId(schoolId);
            replyDTO.setUserId(peopleId+i);
            Reply reply = replyRepository.save(replyDTO.toEntity());
            reply.changeSchool(school);
            reply.changePeople(people);
        }

    }

    //    조회
    @Test
    public void findAllTest() {
        Pageable pageable = PageRequest.of(0,10);
        replyRepository.findBySchoolId(pageable,1L).forEach(o -> log.info("보육원 : " + o.getSchoolUserId() + " 유저 : " + o.getUserId() + " 댓글 : " + o.getReplyContent()));
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
