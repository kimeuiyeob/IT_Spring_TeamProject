package com.app.milestone.repository;

import com.app.milestone.domain.*;
import com.app.milestone.entity.QLike;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.app.milestone.entity.QDonation.donation;
import static com.app.milestone.entity.QLike.like;
import static com.app.milestone.entity.QMoney.money;
import static com.app.milestone.entity.QPeople.people;
import static com.app.milestone.entity.QSchool.school;
import static com.app.milestone.entity.QTalent.talent;

@Repository
@RequiredArgsConstructor
public class LikeCustomRepositoryImpl implements LikeCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Long countByCreatedDate(Pageable pageable, Long peopleId) {
        return jpaQueryFactory.select(like.count())
                .from(like,people,school)
                .where(
                        like.people.userId.eq(peopleId),
                        like.people.userId.eq(people.userId),
                        like.school.userId.eq(school.userId)
                )
                .orderBy(like.createdDate.asc())
                .fetchOne();
    }

    @Override
    public List<LikeDTO> findSchoolLiked (Pageable pageable, Long peopleId){
        return jpaQueryFactory.select(new QLikeDTO(
                like.school.userId,
                like.people.userId,
                like.school.schoolName,
                like.school.introduce.schoolContent,
                like.school.address.schoolAddress
        ))
                .from(like,people,school)
                .where(
                        like.people.userId.eq(peopleId),
                        like.people.userId.eq(people.userId),
                        like.school.userId.eq(school.userId)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(like.createdDate.desc())
                .fetch();
    };



}
