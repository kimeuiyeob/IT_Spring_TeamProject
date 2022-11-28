package com.app.milestone.school;

import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.embeddable.Address;
import com.app.milestone.embeddable.Introduce;
import com.app.milestone.entity.School;
import com.app.milestone.repository.SchoolRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class SchoolTest {
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

//    @Test
//    public void findTest() {
//        List<School> schools = jpaQueryFactory
//                .selectFrom(school)
//                .fetch();
//        schools.forEach(o -> log.info("" + o));
//    }
//
//    @Test
//    public void saveSchoolTest() {
//        SchoolDTO schoolDTO = new SchoolDTO();
//        Address address = new Address();
//        Introduce introduce = new Introduce();
//        schoolDTO = new SchoolDTO();
//        address.setSchoolZipcode("11111");
//        address.setSchoolAddress("바다");
//        address.setSchoolAddressDetail("용궁1동");
//        introduce.setSchoolTitle("안녕하세요");
//        introduce.setSchoolContent("반가워요");
//        schoolDTO.setSchoolName("코끼리 보육원");
//        schoolDTO.setSchoolZipcode("222222");
//        schoolDTO.setSchoolAddress("산");
//        schoolDTO.setSchoolAddressDetail("봉우리1동");
//        schoolDTO.setSchoolTeachers(3);
//        schoolDTO.setSchoolKids(3);
//        schoolDTO.setSchoolBudget(100000);
//        schoolDTO.setSchoolBank("하나은행");
//        schoolDTO.setSchoolAccount("000000000000");
//        schoolDTO.setSchoolPhoneNumber("12312341234");
//        schoolDTO.setSchoolQR(null);
//        schoolDTO.setSchoolTitle("치지직");
//        schoolDTO.setSchoolContent("두루미두루미");
//        schoolDTO.setUserEmail("qwe@qwe.qwe");
//        schoolDTO.setUserName("황지수");
//        schoolDTO.setUserPassword("12341234");
//        schoolDTO.setUserPhoneNumber("01012341234");
//        schoolDTO.setUserProfile(null);
//        schoolDTO.setDonationCount(0);
//
//        schoolRepository.save(schoolDTO.toEntity());
//    }
//
//    @Test
//    public void updateTest() {
//        Address address = new Address();
//        address.setSchoolZipcode("12121212");
//        address.setSchoolAddress("하늘");
//        address.setSchoolAddressDetail("뭉개구름1동");
//        Introduce introduce = new Introduce();
//        introduce.setSchoolTitle("방가방가");
//        introduce.setSchoolContent("반가워요");
//        schoolRepository.findById(3L).get().update("asdf@adsf.asdf", "황지수", "12341234", "01074127412", null, "하마보육원", address, 3, 3, 10000, "국민은행", "1111111111", "028192030",null, introduce);
//    }
//
//    @Test
//    public void deleteTest(){
//        jpaQueryFactory.delete(school).where(school.userId.eq(2L)).execute();
//    }

}
