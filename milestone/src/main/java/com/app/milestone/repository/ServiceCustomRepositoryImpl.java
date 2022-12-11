package com.app.milestone.repository;

import com.app.milestone.domain.MoneyDTO;
import com.app.milestone.domain.QMoneyDTO;
import com.app.milestone.domain.QServiceDTO;
import com.app.milestone.domain.ServiceDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.app.milestone.entity.QMoney.money;
import static com.app.milestone.entity.QPeople.people;
import static com.app.milestone.entity.QSchool.school;
import static com.app.milestone.entity.QService.service;

@Repository
@RequiredArgsConstructor
public class ServiceCustomRepositoryImpl implements ServiceCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

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
        if (keyword!=null) {
            booleanBuilder.or(people.peopleNickname.contains(keyword));
            booleanBuilder.or(school.schoolName.contains(keyword));
        }
        return booleanBuilder;
    }

    //   봉사날짜 최신순
    public List<ServiceDTO> findServiceSearch (Pageable pageable, String keyword){
        return jpaQueryFactory.select(new QServiceDTO(
                service.school.schoolName,
                service.people.peopleNickname,
                service.people.userId,
                service.serviceVisitDate
        ))
                .from(service)
                .where(
                        peopleNicknameAndSchoolNameContaining(keyword)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(service.serviceVisitDate.desc())
                .fetch();
    };

    //   봉사날짜 오래된순
    public List<ServiceDTO> findServiceSearchAsc (Pageable pageable, String keyword){
        return jpaQueryFactory.select(new QServiceDTO(
                service.school.schoolName,
                service.people.peopleNickname,
                service.people.userId,
                service.serviceVisitDate
        ))
                .from(service)
                .where(
                        peopleNicknameAndSchoolNameContaining(keyword)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(service.serviceVisitDate.asc())
                .fetch();
    };

}
