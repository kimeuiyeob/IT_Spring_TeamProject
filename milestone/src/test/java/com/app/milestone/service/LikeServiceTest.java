package com.app.milestone.service;

import com.app.milestone.entity.Like;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class LikeServiceTest {
    @Autowired
    private LikeService likeService;

    //    ====================좋아요====================
    //    내가 누른 좋아요
    @Test
    public void likeSchoolListTest() {
        Long sessionId = 105L;
        likeService.likeSchoolList(sessionId).forEach(o -> log.info("" + o));
    }

    //    좋아요 누름
    @Test
    public void likeSchoolTest() {
//        List<Long> userIds = new ArrayList<>();
//        userIds.add(5L);
//        userIds.add(105L);
        likeService.likeSchool(5L, 132L);
    }

//    좋아요 취소
@Test
public void CancellikeSchoolTest() {
//        List<Long> userIds = new ArrayList<>();
//        userIds.add(5L);
//        userIds.add(105L);
    likeService.cancelLikeSchool(5L, 132L);
}
}
