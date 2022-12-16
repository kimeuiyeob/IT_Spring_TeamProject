package com.app.milestone.repository;

import com.app.milestone.domain.QServiceDTO;
import com.app.milestone.domain.ServiceDTO;
import com.app.milestone.entity.QService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.app.milestone.entity.QDonation.donation;
import static com.app.milestone.entity.QPeople.people;
import static com.app.milestone.entity.QSchool.school;
import static com.app.milestone.entity.QService.service;
import static com.app.milestone.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class ServiceCustomRepositoryImpl implements ServiceCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    //    마이페이지 개인 일정관리
    @Override
    public Long countByCreatedDate(Pageable pageable, Long peopleId) {
        return jpaQueryFactory.select(service.count())
                .from(service)
                .where(service.people.userId.eq(peopleId),
                        service.serviceVisitDate.goe(LocalDateTime.now())
                )
                .orderBy(service.createdDate.asc())
                .fetchOne();
    }

    @Override
    public List<ServiceDTO> findService(Pageable pageable, Long peopleId) {
        return jpaQueryFactory.select(new QServiceDTO(
                service.school.schoolName,
                service.people.peopleNickname,
                service.school.userId,
                service.school.address.schoolAddress,
                service.school.address.schoolAddressDetail,
                service.serviceVisitDate,
                service.donationId,
                service.people.userName,
                service.people.userEmail
        ))
                .from(service)
                .where(
                        service.people.userId.eq(peopleId),
                        service.serviceVisitDate.goe(LocalDateTime.now())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(service.serviceVisitDate.asc())
                .fetch();
    }

    @Override
    public List<ServiceDTO> findVisitDate(Long peopleId) {
        return jpaQueryFactory.select(new QServiceDTO(
                service.school.schoolName,
                service.people.peopleNickname,
                service.people.userId,
                service.school.address.schoolAddress,
                service.school.address.schoolAddressDetail,
                service.serviceVisitDate,
                service.donationId,
                service.people.userName,
                service.people.userEmail))
                .from(service)
                .where(
                        service.people.userId.eq(peopleId),
                        service.serviceVisitDate.goe(LocalDateTime.now())
                )
                .fetch();
    }


    //    마이페이지 보육원 일정관리
    @Override
    public Long countByCreatedDate1(Pageable pageable, Long schoolId) {
        return jpaQueryFactory.select(service.count())
                .from(service)
                .where(service.school.userId.eq(schoolId),
                        service.serviceVisitDate.goe(LocalDateTime.now())
                )
                .orderBy(service.createdDate.asc())
                .fetchOne();
    }

    @Override
    public List<ServiceDTO> findService1(Pageable pageable, Long schoolId) {
        return jpaQueryFactory.select(new QServiceDTO(
                service.school.schoolName,
                service.people.peopleNickname,
                service.people.userId,
                service.school.address.schoolAddress,
                service.school.address.schoolAddressDetail,
                service.serviceVisitDate,
                service.donationId,
                service.people.userName,
                service.people.userEmail
        ))
                .from(service)
                .where(
                        service.school.userId.eq(schoolId),
                        service.serviceVisitDate.goe(LocalDateTime.now())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(service.serviceVisitDate.asc())
                .fetch();
    }

    @Override
    public List<ServiceDTO> findVisitDate1(Long schoolId) {
        return jpaQueryFactory.select(new QServiceDTO(
                service.school.schoolName,
                service.people.peopleNickname,
                service.people.userId,
                service.school.address.schoolAddress,
                service.school.address.schoolAddressDetail,
                service.serviceVisitDate,
                service.donationId,
                service.people.userName,
                service.people.userEmail))
                .from(service)
                .where(service.school.userId.eq(schoolId),
                        service.serviceVisitDate.goe(LocalDateTime.now())
                )
                .fetch();
    }

//========================황지수======================
    //  방문기부 랭킹 정렬
    @Override
    public List<Tuple> sortByVisitRank() {
        List<Tuple> tuples = new ArrayList<>();
        Tuple temp = null;

        tuples = jpaQueryFactory.select(service.createdDate.count(), service.people.userId)
                .from(service)
                .groupBy(people.userId)
                .fetch();

//        sortTuples
        for (int i = 0; i < tuples.size(); i++) {
            for (int j = 0; j < tuples.size(); j++) {
                String icash = tuples.get(i).get(0, Long.class) + "";
                String jcash = tuples.get(j).get(0, Long.class) + "";
                Long longIcash = Long.valueOf(icash);
                Long longJcash = Long.valueOf(jcash);
                if (longIcash >= longJcash) {
                    temp = tuples.get(i);
                    tuples.set(i, tuples.get(j));
                    tuples.set(j, temp);
                }
            }
        }
        return tuples;
    }

//    중복신청 확인
    @Override
    public Long checkOverlap(Long userId, ServiceDTO serviceDTO){
        return jpaQueryFactory.select(service.count())
                .from(service)
                .where(
                    service.people.userId.eq(userId).and(service.serviceVisitDate.eq(serviceDTO.getServiceVisitDate()))
                )
                .fetchOne();
    }
//========================/황지수======================


    /* 관리자 ==================================================*/

    @Override
    public Long countByCreatedDate(Pageable pageable, String keyword) {
        return jpaQueryFactory.select(service.count())
                .from(service)
                .where(
                        peopleNicknameAndSchoolNameContaining(keyword)
                )
                .orderBy(service.createdDate.asc())
                .fetchOne();
    }

    // 통합검색
    private BooleanBuilder peopleNicknameAndSchoolNameContaining(String keyword) {
        if (keyword == null) {
            return null;
        }
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (keyword != null) {
            booleanBuilder.or(people.peopleNickname.contains(keyword));
            booleanBuilder.or(school.schoolName.contains(keyword));
        }
        return booleanBuilder;
    }

    //   봉사날짜 최신순
    public List<ServiceDTO> findServiceSearch(Pageable pageable, String keyword) {
        return jpaQueryFactory.select(new QServiceDTO(
                service.school.schoolName,
                service.people.peopleNickname,
                service.school.userId,
                service.school.address.schoolAddress,
                service.school.address.schoolAddressDetail,
                service.serviceVisitDate,
                service.donationId,
                service.people.userName,
                service.people.userEmail
        ))
                .from(service, donation, people, school, user)
                .where(
                        service.donationId.eq(donation.donationId),
                        donation.people.userId.eq(people.userId),
                        donation.school.userId.eq(school.userId),
                        people.userId.eq(user.userId),
                        peopleNicknameAndSchoolNameContaining(keyword)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(service.serviceVisitDate.desc())
                .fetch();
    }

    ;

    //   봉사날짜 오래된순
    public List<ServiceDTO> findServiceSearchAsc(Pageable pageable, String keyword) {
        return jpaQueryFactory.select(new QServiceDTO(
                service.school.schoolName,
                service.people.peopleNickname,
                service.school.userId,
                service.school.address.schoolAddress,
                service.school.address.schoolAddressDetail,
                service.serviceVisitDate,
                service.donationId,
                service.people.userName,
                service.people.userEmail
        ))
                .from(service, donation, people, school, user)
                .where(
                        service.donationId.eq(donation.donationId),
                        donation.people.userId.eq(people.userId),
                        donation.school.userId.eq(school.userId),
                        people.userId.eq(user.userId),
                        peopleNicknameAndSchoolNameContaining(keyword)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(service.serviceVisitDate.asc())
                .fetch();
    }

    ;

}
