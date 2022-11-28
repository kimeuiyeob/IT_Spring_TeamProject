package com.app.milestone.service;

import com.app.milestone.domain.PeopleDTO;
import com.app.milestone.domain.ServiceDTO;
import com.app.milestone.entity.People;
import com.app.milestone.entity.Service;
import com.app.milestone.repository.PeopleRepository;
import com.app.milestone.repository.ServiceRepository;
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

    private JPAQueryFactory jpaQueryFactory;

//    @Test
//    public void saveTest() {
//        ServiceDTO serviceDTO = new ServiceDTO(LocalDateTime.now());
//        serviceRepository.save(serviceDTO.toEntity());
//    }
//
//    @Test
//    public void updateTest() {
//        Service service = serviceRepository.findById(8L).get();
//
//        service.update(LocalDateTime.now());
//    }
//
//    @Test
//    public void findTest() {
//        assertThat(serviceRepository.findById(8L).get().getDonationId()).isEqualTo(8L);
//    }
//
//    @Test
//    public void deleteTest() {
//        serviceRepository.deleteById(8L);
//    }
}
