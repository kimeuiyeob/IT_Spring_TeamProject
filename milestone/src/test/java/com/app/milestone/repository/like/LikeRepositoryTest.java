package com.app.milestone.repository.like;

import com.app.milestone.repository.LikeRepository;
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
public class LikeRepositoryTest {
    @Autowired
    private LikeRepository likeRepository;

    @Test
    public void deleteByPeopleIdAndSchoolIdTest(){
        Long sessionId = 132L;
        Long userId = 5L;
        likeRepository.deleteByPeopleIdAndSchoolId(sessionId,userId);
    }
}
