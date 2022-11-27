package com.app.milestone.reply;

import com.app.milestone.domain.ReplyDTO;
import com.app.milestone.entity.Reply;
import com.app.milestone.repository.PeopleRepository;
import com.app.milestone.repository.ReplyRepository;
import com.app.milestone.repository.SchoolRepository;
import com.app.milestone.repository.UserRepository;
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
public class ReplyTest {
    @Autowired
    private ReplyRepository replyRepository;
    private JPAQueryFactory jpaQueryFactory;

    @Test
    public void saveTest() {
//        userRepository.findById(6L);
//        SchoolDTO schoolDTO = schoolRepository.findById(6L);
//        PeopleDTO peopleDTO = peopleRepository.findById(5L);

        ReplyDTO replyDTO = new ReplyDTO();
        replyDTO.setReplyContent("댓글내용");

//        replyDTO.setPeopleDTO(peopleDTO);
//        replyDTO.setSchoolDTO(schoolDTO);

        replyRepository.save(replyDTO.toEntity());
    }

    @Test
    public void findTest() {
        assertThat(replyRepository.findById(7L).get().getReplyContent()).isEqualTo("댓글내용");
    }

    @Test
    public void updateTest() {
        //asserThat 오류가 났을 때 친절히 알려줌~~!!!
        Reply reply = replyRepository.findById(7L).get();
        reply.update("우린 백작업을 할 수 있다");
    }

    @Test
    public void deleteTest() {
        replyRepository.deleteById(7L);
    }
}
