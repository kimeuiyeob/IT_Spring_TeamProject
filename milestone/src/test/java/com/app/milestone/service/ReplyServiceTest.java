package com.app.milestone.service;

import com.app.milestone.domain.ReplyDTO;
import com.app.milestone.entity.Reply;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class ReplyServiceTest {
    @Autowired
    private ReplyService replyService;

    //    추가
//    @Test
//    public void registerTest() {
//        ReplyDTO replyDTO = new ReplyDTO();
//        replyDTO.setPeopleUserId(101L);
//        replyDTO.setSchoolUserId(5L);
//        replyDTO.setReplyContent("이것도 댓글이예요");
//        replyService.register(replyDTO);
//    }

    //    조회
    @Test
    public void showAll() {
        replyService.showAll(1,5L).forEach(o -> log.info("모든"+o+"작성자" + o.getPeopleNickName() + "댓글" + o.getReplyContent()));
    }

    //    수정
    @Test
    public void modify() {
        ReplyDTO replyDTO = new ReplyDTO();
        replyDTO.setReplyId(561L);
        replyDTO.setReplyContent("저는 이제 바뀔 거예요");
        replyService.modify(replyDTO);
    }

    //    삭제
    @Test
    public void remove() {
        replyService.remove(568L);
    }
}
