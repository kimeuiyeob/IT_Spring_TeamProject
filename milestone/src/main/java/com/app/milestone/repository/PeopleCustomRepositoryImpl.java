package com.app.milestone.repository;

import com.app.milestone.domain.PeopleDTO;
import com.app.milestone.domain.QPeopleDTO;
import com.app.milestone.domain.Search;
import com.app.milestone.entity.QTalent;
import com.app.milestone.entity.QUser;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.app.milestone.entity.QMoney.*;
import static com.app.milestone.entity.QPeople.*;
import static com.app.milestone.entity.QSchool.school;
import static com.app.milestone.entity.QService.*;
import static com.app.milestone.entity.QTalent.*;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PeopleCustomRepositoryImpl implements PeopleCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    // 개인 한 명정보 조회
    @Override
    public PeopleDTO findInfoById(Long userId){
        return jpaQueryFactory.select(new QPeopleDTO(
                people.userId,
                people.peopleNickname,
                people.userEmail,
                people.userName,
                people.userPassword,
                people.userPhoneNumber,
                people.donationCount,
                people.createdDate
        )).from(people)
                .where(people.userId.eq(userId))
                .fetchOne();

    }

    //  기부금 랭킹 정렬
    @Override
    public List<Tuple> sortByMoneyCash() {
        List<Tuple> tuples = new ArrayList<>();
        Tuple temp = null;

        tuples = jpaQueryFactory.select(money.moneyCash.sum(), money.people.userId)
                .from(money)
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

    //  재능기부 랭킹 정렬
    @Override
    public List<Tuple> sortBytalentRank() {
        List<Tuple> tuples = new ArrayList<>();
        Tuple temp = null;

        tuples = jpaQueryFactory.select(talent.talentAbleDate.count(), talent.people.userId)
                .from(talent)
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




    /*=================관리자 페이지=================*/
    //    검색 결과 count
    @Override
    public Long countByCreatedDate(Pageable pageable, String keyword) {
        return jpaQueryFactory.select(people.count())
                .from(people)
                .where(
                    userNameAndNicknameContaining(keyword)
                )
                .orderBy(people.createdDate.asc())
                .fetchOne();
    }

    @Override
    public List<PeopleDTO> findPeopleSearch(Pageable pageable, String keyword){
        return jpaQueryFactory.select(new QPeopleDTO(
                people.userId,
                people.peopleNickname,
                people.userEmail,
                people.userName,
                people.userPassword,
                people.userPhoneNumber,
                people.donationCount,
                people.createdDate
        )).from(people)
                .where(
                    userNameAndNicknameContaining(keyword)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(people.createdDate.desc())
                .fetch();
    }

    @Override
    public List<PeopleDTO> findPeopleSearchAsc(Pageable pageable, String keyword){
        return jpaQueryFactory.select(new QPeopleDTO(
                people.userId,
                people.peopleNickname,
                people.userEmail,
                people.userName,
                people.userPassword,
                people.userPhoneNumber,
                people.donationCount,
                people.createdDate
        )).from(people)
                .where(
                    userNameAndNicknameContaining(keyword)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(people.createdDate.asc())
                .fetch();
    }

    // 통합검색
    private BooleanBuilder userNameAndNicknameContaining(String keyword) {
        if (keyword == null) {
            return null;
        }
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (keyword!=null) {
            booleanBuilder.or(people.userName.contains(keyword));
            booleanBuilder.or(people.peopleNickname.contains(keyword));
        }
        return booleanBuilder;
    }
}
