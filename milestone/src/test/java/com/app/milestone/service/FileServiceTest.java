package com.app.milestone.service;

import com.app.milestone.domain.FileDTO;
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
public class FileServiceTest {
    @Autowired
    private FileService fileService;

    //     추가
    @Test
    public void registerTest() {
        FileDTO fileDTO = new FileDTO();
        fileDTO.setUserId(105L);
        fileDTO.setFileName("testImg.png");
        fileDTO.setFilePath("test");
        fileDTO.setFileUuid("abc111");
        fileDTO.setFileType(FileType.profile);
        fileDTO.setFileSize(30L);
        fileService.register(fileDTO);
    }

    //    삭제
    @Test
    public void removeTest() {
        fileService.remove(342L);
    }

    //    전체조회
    @Test
    public void showAllTest() {
        fileService.showAll(105L).forEach(o -> log.info("==============asd" + o));
    }

}
