package com.app.milestone.people;

import com.app.milestone.domain.PeopleDTO;
import com.app.milestone.entity.People;
import com.app.milestone.entity.User;
import com.app.milestone.repository.PeopleRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
public class PeopleTest {
    @Autowired
    private PeopleRepository peopleRepository;

    private JPAQueryFactory jpaQueryFactory;

//    @Test
//    public void saveTest() {
//        PeopleDTO peopleDTO = new PeopleDTO("스폰지밥", "spj@naver.com", "황지수", "12341234", "01085319498", null, 0);
//        peopleRepository.save(peopleDTO.toEntity());
//    }
//
//    @Test
//    public void updateTest() {
//        People people = peopleRepository.findById(1L).get();
//
//        people.update("징징이");
////        people.update("naver","황지수","12341234","01085319498",null);
//    }
//
//    @Test
//    public void findTest() {
//        assertThat(peopleRepository.findById(2L).get().getUserName()).isEqualTo("황지수");
//    }
//
//    @Test
//    public void deleteTest() {
//        peopleRepository.deleteById(1L);
//    }
}
