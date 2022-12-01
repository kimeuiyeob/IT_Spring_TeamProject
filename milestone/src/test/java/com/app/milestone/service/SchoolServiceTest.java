package com.app.milestone.service;

import com.app.milestone.domain.Search;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class SchoolServiceTest {
    @Autowired
    private SchoolService schoolservice;

    @Test
    public void needHelpListTest() {
        schoolservice.needHelpList().forEach(o -> log.info("" + o.getDonationCount()));
    }

    @Test
    public void schoolListTest() {
        Pageable pageable = PageRequest.of(0, 10);
        Search search = new Search();
        search.setSchoolName("코끼리");
        search.setSchoolAddress(new ArrayList<>());
        search.getSchoolAddress().add("바다");
        search.getSchoolAddress().add("사막");
        schoolservice.schoolList(pageable, search).forEach(o -> log.info("adsdafas" + o));
    }

}
