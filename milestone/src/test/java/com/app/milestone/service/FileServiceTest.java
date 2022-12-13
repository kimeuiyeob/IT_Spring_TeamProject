package com.app.milestone.service;

import com.app.milestone.domain.FileDTO;
import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.domain.Search;
import com.app.milestone.entity.School;
import com.app.milestone.type.FileType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class FileServiceTest {
    @Autowired
    private FileService fileService;

    /*--------------------------------------*/
    @Autowired
    private SchoolService schoolService;

    @Test
    public void testTest() {

        Pageable pageable = PageRequest.of(0, 10);
        Pageable pageable1 = PageRequest.of(0, 10);
        Search search = new Search();
        search.setSchoolAddress(new ArrayList<>());
        search.getSchoolAddress().add(null);
//        List<SchoolDTO> list = schoolService.schoolList(pageable, search);
        int start = (int) pageable.getOffset();
//        int end = Math.min((start + pageable.getPageSize()), list.size());
//        Page<SchoolDTO> page = new PageImpl<>(list.subList(start,end),pageable,100);
//        Page<SchoolDTO> page = new PageImpl<>(list);
//        log.info("start" + start);
//        log.info("end" + end);
//        log.info("getTotalPages" + page.getTotalPages());
//        log.info("getNumber" + page.getNumber());
//        log.info("hasNext" + page.hasNext());
//        log.info("hasPrevious" + page.hasPrevious());
//        log.info("isFirst" + page.isFirst());
//        log.info("isLast" + page.isLast());
//        page.get().forEach(o -> log.info("" + o));

//        log.info("" + pageable.getOffset() + pageable.isPaged() + pageable.getPageNumber() + pageable.getSort());
    }


    //     추가
//    @Test
//    public void registerTest() {
//        FileDTO fileDTO = new FileDTO();
//        fileDTO.setUserId(105L);
//        fileDTO.setFileName("testImg.png");
//        fileDTO.setFilePath("test");
//        fileDTO.setFileUuid("abc111");
//        fileDTO.setFileType(FileType.profile);
//        fileDTO.setFileSize(30L);
//        fileService.register(fileDTO);
//    }

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
