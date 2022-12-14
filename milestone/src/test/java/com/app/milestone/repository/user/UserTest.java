package com.app.milestone.repository.user;

import com.app.milestone.entity.QPeople;
import com.app.milestone.entity.QSchool;
import com.app.milestone.entity.QUser;
import com.app.milestone.repository.TalentRepository;
import com.app.milestone.repository.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.DiscriminatorColumn;

import static com.app.milestone.entity.QUser.user;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class UserTest {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void test(){
        log.info("==============");
        log.info("==============getSimpleName===" + userRepository.findById(2L).get().getClass().getSimpleName().toLowerCase());
        log.info("==============getSimpleName===" + userRepository.findById(200L).get().getClass().getSimpleName().toLowerCase());
        log.info("==============");
    }

//    회원 전체목록 조회
    @Test
    public void getListTest() {
        jpaQueryFactory.selectFrom(user).fetch().forEach(user -> log.info("\n이름,이메일 : "+user.getUserName()+user.getUserEmail()
                +"\n연락처 :"+user.getUserPhoneNumber()+"\n사용자 형태 : "+user.getClass().getAnnotation(DiscriminatorColumn.class)+"\n가입날짜 : "+user.getCreatedDate()));

    }

//    가입날짜순으로 조회
    @Test
    public void getListByDateTest() {
        jpaQueryFactory.selectFrom(user)
                .orderBy(user.createdDate.desc())
                .fetch()
                .forEach(o -> log.info("\n이름,이메일 : "+o.getUserName()+", "+o.getUserEmail()
                +"\n연락처 :"+o.getUserPhoneNumber()+"\n사용자 형태 : "+o.getClass().getTypeName()+"\n가입날짜 : "+o.getCreatedDate()));
    }


//    회원 삭제
    @Test
    public void deleteTest() {
        for (int i = 171; i<201; i++){
            userRepository.deleteById(Long.valueOf(i));
        }
    }

}
