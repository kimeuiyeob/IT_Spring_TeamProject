package com.app.milestone.people;

import com.app.milestone.domain.PeopleDTO;
import com.app.milestone.domain.QPeopleDTO;
import com.app.milestone.entity.People;
import com.app.milestone.entity.QPeople;
import com.app.milestone.entity.User;
import com.app.milestone.repository.PeopleRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.app.milestone.entity.QPeople.people;

@Slf4j
@SpringBootTest
@Transactional
@Rollback(false)
public class PeopleTest {
    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Test
    public void saveTest() {
        PeopleDTO peopleDTO = new PeopleDTO("뚱이", "DDe@naver.com", "불가사리", "12341234", "01013131313", 0);
        peopleRepository.save(peopleDTO.toEntity());
    }

    @Test
    public void updateTest() {
        People people = peopleRepository.findById(2L).get();
        people.update("슬픈 뚱이");
    }

    @Test
    public void findTest() {
//        assertThat(peopleRepository.findById(2L).get().getUserName()).isEqualTo("불가사리");
        List<People> people  = jpaQueryFactory.selectFrom(QPeople.people)
                .where(QPeople.people.peopleNickname.eq("슬픈 뚱이"))
                .limit(1)
                .fetch();

        people.stream().map(People::toString).forEach(log::info);
    }

    @Test
    public void deleteTest() {
        peopleRepository.deleteById(1L);
    }
}
