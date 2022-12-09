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
public class TalentServiceTest {

    @Autowired
    private TalentService talentService;

    @Test
    public void TalentListTest() {

        String[] locations = {"강원도", "서울"};
        Pageable pageable = PageRequest.of(0, 10);
        Search search = new Search();

        search.setSchoolAddress(new ArrayList<>());

        for (String location : locations) {
            search.getSchoolAddress().add(location);
        }

        search.setTalentTitle("돌고래");
        search.setTalentCategory("운동");
    }




}
