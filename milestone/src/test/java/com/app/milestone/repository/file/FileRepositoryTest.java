package com.app.milestone.repository.file;

import com.app.milestone.domain.FileDTO;
import com.app.milestone.entity.File;
import com.app.milestone.entity.People;
import com.app.milestone.entity.User;
import com.app.milestone.repository.FileRepository;
import com.app.milestone.repository.PeopleRepository;
import com.app.milestone.type.FileType;
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
public class FileRepositoryTest {
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private PeopleRepository peopleRepository;

    //    사진 추가
    @Test
    public void saveTest() {
        People people = peopleRepository.findById(105L).get();
        File file = new File("testImg.png", "test", "abc111", 30L, FileType.profile, people);
        fileRepository.save(file);
    }

    //    사진 조회
    @Test
    public void findTest() {
        log.info("============asd" + fileRepository.findByUserId(105L));
    }

    //    사진 삭제
    @Test
    public void deleteTest() {
        fileRepository.deleteById(339L);
    }

}
