package com.app.milestone.service;

import com.app.milestone.domain.Search;
import com.app.milestone.domain.TalentDTO;
import com.app.milestone.entity.People;
import com.app.milestone.entity.Talent;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

//        talentService.talentList(pageable, search)
//                .forEach(o -> log.info(
//                        "  Title : " + o.getTalentTitle() +
//                                "  Content : " + o.getTalentContent() +
//                                "  Category :" + o.getTalentCategory() +
//                                "  Place : " + o.getTalentPlace() +
//                                "  AbleDate : " + o.getTalentAbleDate()));
    }

//    @Test
//    public void writeSaveTest() {
//        talentService.writeSave();
//    }


    @Test
    public void educationTest() {
        talentService.educationList().stream().map(o -> o.toString()).forEach(o -> log.info("값은: " + o));
    }


}
