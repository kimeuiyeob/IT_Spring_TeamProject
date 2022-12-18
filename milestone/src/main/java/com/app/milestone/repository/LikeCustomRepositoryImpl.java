package com.app.milestone.repository;

import com.app.milestone.domain.*;
import com.app.milestone.entity.QLike;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

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

    /*==========================정서림===========================*/
    //  전체목록 총 개수 : 처음 목록을 불러오면서 List를 Page타입으로 페이징처리시 전체개수를 구하는 메소드입니다.
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

    //  검색목록 총 개수 : 이름과 주소 검색한 경우, 위와같이 페이징처리를 위해 전체개수를 구하는 메소드입니다.
    @Override
    public Long countByCreatedDate2(Pageable pageable, Long peopleId, Search search) {
        return jpaQueryFactory.select(like.count())
                .from(like,people,school)
                .where(
                        like.people.userId.eq(peopleId),
                        like.people.userId.eq(people.userId),
                        like.school.userId.eq(school.userId),
                        schoolNameContaining(search.getSchoolName()),
                        schoolAddressContaining(search.getSchoolAddress())
                )
                .orderBy(like.createdDate.desc())
                .fetchOne();
    }


    // 전체목록
    @Override
    public List<LikeDTO> findSchoolLiked (Pageable pageable, Long peopleId){
        return jpaQueryFactory.select(new QLikeDTO(
                like.likeId,
                like.school.userId,
                like.people.userId,
                like.school.schoolName,
                like.school.introduce.schoolContent,
                like.school.address.schoolAddress,
                like.school.schoolQR
        ))
                .from(like,people,school)
                .where(
                        like.people.userId.eq(peopleId),    //로그인한 회원의 아이디로 찾아온 찜목록
                        like.people.userId.eq(people.userId),
                        like.school.userId.eq(school.userId)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(like.createdDate.desc())
                .fetch();
    };

    //  검색목록
    @Override
    public List<LikeDTO> findSchoolLikedSearch (Pageable pageable, Long peopleId, Search search){
        return jpaQueryFactory.select(new QLikeDTO(
                like.likeId,
                like.school.userId,
                like.people.userId,
                like.school.schoolName,
                like.school.introduce.schoolContent,
                like.school.address.schoolAddress,
                like.school.schoolQR
        ))
                .from(like,people,school)
                .where(
                        like.people.userId.eq(peopleId),
                        like.people.userId.eq(people.userId),
                        like.school.userId.eq(school.userId),
                        schoolNameContaining(search.getSchoolName()),
                        schoolAddressContaining(search.getSchoolAddress())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(like.createdDate.desc())
                .fetch();
    }

    //    SchoolName 검색
    private BooleanExpression schoolNameContaining(String schoolName) {
        return StringUtils.hasText(schoolName) ? school.schoolName.contains(schoolName) : null;
    }

    //    지역검색
    private BooleanBuilder schoolAddressContaining(List<String> schoolAddresses) {
        if (schoolAddresses.get(0) == null) {
            return null;
        }
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        for (String schoolAddress : schoolAddresses) {
            if (schoolAddress.equals("서울")) {
                booleanBuilder.or(school.address.schoolAddress.like("서울%"));
            }
            if (schoolAddress.equals("인천")) {
                booleanBuilder.or(school.address.schoolAddress.like("인천%"));
            }
            if (schoolAddress.equals("경기도")) {
                booleanBuilder.or(school.address.schoolAddress.like("경기%"));
            }
            if (schoolAddress.equals("강원도")) {
                booleanBuilder.or(school.address.schoolAddress.like("강원%"));
            }
            if (schoolAddress.equals("충청도")) {
                booleanBuilder.or(school.address.schoolAddress.like("충북%").or(school.address.schoolAddress.like("충남%")).or(school.address.schoolAddress.like("세종%")).or(school.address.schoolAddress.like("대전%")));
            }
            if (schoolAddress.equals("전라도")) {
                booleanBuilder.or(school.address.schoolAddress.like("전북%").or(school.address.schoolAddress.like("전남%")).or(school.address.schoolAddress.like("광주%")));
            }
            if (schoolAddress.equals("경상도")) {
                booleanBuilder.or(school.address.schoolAddress.like("경북%").or(school.address.schoolAddress.like("경남%")).or(school.address.schoolAddress.like("부산%")).or(school.address.schoolAddress.like("울산%")).or(school.address.schoolAddress.like("대구%")));
            }
            if (schoolAddress.equals("제주도")) {
                booleanBuilder.or(school.address.schoolAddress.like("제주%"));
            }
        }
        return booleanBuilder;
    }


}
