package com.app.milestone.repository;

import com.app.milestone.domain.MoneyDTO;
import com.app.milestone.domain.QMoneyDTO;
import com.app.milestone.entity.Money;
import com.app.milestone.entity.People;
import com.app.milestone.entity.QMoney;
import com.app.milestone.entity.QSchool;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.app.milestone.entity.QMoney.*;
import static com.app.milestone.entity.QPeople.people;
import static com.app.milestone.entity.QSchool.*;

@Repository
@RequiredArgsConstructor
public class MoneyCustomRepositoryImpl implements MoneyCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    //    기부받은 내역
    @Override
    public List<MoneyDTO> findByCreateDateByUserId(Long userId) {
        return jpaQueryFactory.select(new QMoneyDTO(
                money.people.peopleNickname,
                money.school.userId,
                money.moneyCash
        )).from(money)
                .where(money.school.userId.eq(userId))
                .orderBy(money.createdDate.desc())
                .fetch();
    }

    //  전체 기부금 랭킹 정렬
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

    //  보육원 하나 기부금 랭킹 정렬
    @Override
    public List<Tuple> moneyRankingByOne(Long userId) {
        List<Tuple> tuples = new ArrayList<>();
        Tuple temp = null;

        tuples = jpaQueryFactory.select(money.moneyCash.sum(), money.people.userId)
                .from(money)
                .where(school.userId.eq(userId))
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
}
