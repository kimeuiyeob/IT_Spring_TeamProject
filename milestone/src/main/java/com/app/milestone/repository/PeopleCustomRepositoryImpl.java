package com.app.milestone.repository;

import com.app.milestone.domain.PeopleDTO;
import com.app.milestone.domain.QPeopleDTO;
import com.app.milestone.entity.QFile;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.app.milestone.entity.QFile.file;
import static com.app.milestone.entity.QLike.like;
import static com.app.milestone.entity.QPeople.people;
import static com.app.milestone.entity.QSchool.school;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PeopleCustomRepositoryImpl implements PeopleCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    // 개인 한 명정보 조회
    @Override
    public PeopleDTO findInfoById(Long userId) {
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
                        people.userId.eq(userId)
                )
                .fetchOne();

    }


    /*==========================정서림===========================*/
    //    검색 결과 총개수 : 검색목록을 불러오면서 List를 Page타입으로 페이징처리시 전체개수를 구하는 메소드입니다.
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

    //      전체목록 및 검색목록
    @Override
    public List<PeopleDTO> findPeopleSearch(Pageable pageable, String keyword) {
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
                .leftJoin(file)     //회원 프로필을 불러오기위한 join으로, 사진이 한장인 경우에만 가능합니다.
                .on(people.userId.eq(file.user.userId))
                .where(
                        userNameAndNicknameContaining(keyword)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(people.createdDate.desc())
                .fetch();
    }

    @Override
    public List<PeopleDTO> findPeopleSearchAsc(Pageable pageable, String keyword) {
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
              .leftJoin(file)
              .on(people.userId.eq(file.user.userId))
              .where(
                        userNameAndNicknameContaining(keyword)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(people.createdDate.asc())
                .fetch();
    }

    // 이름 및 닉네임 검색
    private BooleanBuilder userNameAndNicknameContaining(String keyword) {
        if (keyword == null) {
            return null;
        }
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (keyword != null) {
            booleanBuilder.or(people.userName.contains(keyword));
            booleanBuilder.or(people.peopleNickname.contains(keyword));
        }
        return booleanBuilder;
    }

}
