package com.app.milestone.service;

import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.domain.ServiceDTO;
import com.app.milestone.entity.School;
import com.app.milestone.entity.Service;
import com.app.milestone.repository.SchoolRepository;
import com.app.milestone.repository.ServiceRepository;
import com.app.milestone.repository.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(false)
public class ServiceTest {
    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    //    보육원에 서비스 신청하기
    @Test
    public void saveTest() {
        SchoolDTO schoolDTO = new SchoolDTO();

        School school = schoolRepository.findById(11L).get();


        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setServiceVisitDate(LocalDateTime.of(2022, 12, 4, 22, 39));


        Service service = serviceDTO.toEntity();
        serviceRepository.save(service);
        service.changeSchool(school);
    }
//    안켜도됨
//UPDATE TBL_USER
//    SET DONATION_COUNT = (SELECT COUNT(DONATION_ID) FROM TBL_DONATION WHERE SCHOOL_USER_ID = 11)
//    WHERE USER_ID = 11;


    @Test
    public void findTest() {
        assertThat(serviceRepository.findById(8L).get().getDonationId()).isEqualTo(8L);
    }

//    @Test
//    public void deleteTest() {
//        serviceRepository.deleteById(8L);
//    }
}
