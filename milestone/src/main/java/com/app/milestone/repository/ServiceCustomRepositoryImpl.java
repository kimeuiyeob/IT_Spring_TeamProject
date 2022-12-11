package com.app.milestone.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.app.milestone.entity.QPeople.people;
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

}
