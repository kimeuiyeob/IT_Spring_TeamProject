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
                people.createdDate,
                file.fileName,
                file.filePath,
                file.fileUuid
        )).from(people,file)
                .where(
                        people.userId.eq(userId),
                        people.userId.eq(file.user.userId)
                )
                .fetchOne();

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
    public List<PeopleDTO> findPeopleSearch(Pageable pageable, String keyword) {
        return jpaQueryFactory.select(new QPeopleDTO(
                people.userId,
                people.peopleNickname,
                people.userEmail,
                people.userName,
                people.userPassword,
                people.userPhoneNumber,
                people.donationCount,
                people.createdDate,
                file.fileName,
                file.filePath,
                file.fileUuid
        )).from(people)
                .leftJoin(file)
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
                people.createdDate,
                file.fileName,
                file.filePath,
                file.fileUuid
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

    // 통합검색
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
