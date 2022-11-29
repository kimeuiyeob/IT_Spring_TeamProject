package com.app.milestone.repository;

import com.app.milestone.domain.Search;
import com.app.milestone.entity.School;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.app.milestone.entity.QSchool.school;

@Repository
@RequiredArgsConstructor
public class SchoolCustomRepositoryImpl implements SchoolCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    //  메인 도움이 필요해요
    @Override
    public List<School> findAllByDonationCount(Pageable pageable) {
        return jpaQueryFactory.selectFrom(school)
                .orderBy(school.donationCount.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    //    보육원 목록(최신순)
    @Override
    public List<School> findAllByCreatedDate(Pageable pageable, Search search) {
        return jpaQueryFactory.selectFrom(school)
                .where(
//                        schoolNameContainingAndLocationContaining(search.getSchoolName(), search.getSchoolAddress())
                        schoolNameContaining(search.getSchoolName()),
//                        schoolAddressContaining(search.getSchoolName())
                        school.address.schoolAddress.in(search.getSchoolAddress())
                )
                .orderBy(school.createdDate.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

//
    private BooleanExpression schoolNameContaining(String schoolName) {
        return StringUtils.hasText(schoolName) ? school.schoolName.contains(schoolName) : null;
    }

//    저는 안썻는데 쓰실거 같아서 남겨둡니다. -황지수
    private BooleanExpression schoolAddressContaining(String schoolAddress) {
        return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.contains(schoolAddress) : null;
    }


}
