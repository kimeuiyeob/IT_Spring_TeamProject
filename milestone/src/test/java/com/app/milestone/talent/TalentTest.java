package com.app.milestone.talent;

import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.domain.TalentDTO;
import com.app.milestone.embeddable.Address;
import com.app.milestone.embeddable.Introduce;
import com.app.milestone.entity.People;
import com.app.milestone.entity.School;
import com.app.milestone.entity.Talent;
import com.app.milestone.repository.SchoolRepository;
import com.app.milestone.repository.TalentRepository;
import com.app.milestone.type.Category;
import com.app.milestone.type.Place;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class TalentTest {

    @Autowired
    private TalentRepository talentRepository;

    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private SchoolRepository schoolRepository;


    @Test
    public void saveTest() {
        TalentDTO talentDTO = new TalentDTO("제목","내용", LocalDateTime.now(), Category.IT, Place.강원도);
        talentRepository.save(talentDTO.toEntity());
    }

    @Test
    public void findTest() {
        Talent talent = talentRepository.findById(1L).get();
    }

    @Test
    public void updateTest() {
        Talent talent = talentRepository.findById(1L).get();
        talent.update("나는야제목","나는야내용",LocalDateTime.now(),Category.교육,Place.서울);
    }

    @Test
    public void deleteTest() {
           talentRepository.deleteById(1L);
    }

   /* @Test
    public void findTest2() {
        List<Talent> talents = jpaQueryFactory.selectFrom(talent).fetch();
        talents.forEach(o -> log.info("" + o));
    }*/


}
