package com.app.milestone.repository;

import com.app.milestone.domain.QSchoolDTO;
import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.domain.Search;
import com.app.milestone.entity.QSchool;
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
//    @Override
//    public List<School> findAllByDonationCount(Pageable pageable) {
//        return jpaQueryFactory.selectFrom(school)
//                .orderBy(school.donationCount.asc())
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//    }
    //  메인 도움이 필요해요
//    @Override
//    public List<School> findAllByDonationCount() {
//        return jpaQueryFactory.selectFrom(school)
//                .orderBy(school.donationCount.asc())
//                .offset(0)
//                .limit(5)
//                .fetch();
//    }
    //  메인 도움이 필요해요(쿼리프로젝션 버전)
    @Override
    public List<SchoolDTO> findAllByDonationCount() {
        return jpaQueryFactory.select(new QSchoolDTO(
                school.schoolName,
                school.address.schoolZipcode,
                school.address.schoolAddress,
                school.address.schoolAddressDetail,
                school.schoolTeachers,
                school.schoolKids,
                school.schoolBudget,
                school.schoolBank,
                school.schoolAccount,
                school.schoolPhoneNumber,
                school.schoolQR,
                school.schoolName,
                school.schoolName,
                school.userEmail,
                school.userName,
                school.userPassword,
                school.userPhoneNumber,
                school.donationCount
        )).from(school)
                .orderBy(school.donationCount.asc())
                .offset(0)
                .limit(5)
                .fetch();
    }

        //    보육원 목록(최신순)
//    @Override
//    public List<School> findAllByCreatedDate(Pageable pageable, Search search) {
//        return jpaQueryFactory.selectFrom(school)
//                .where(
////                        schoolNameContainingAndLocationContaining(search.getSchoolName(), search.getSchoolAddress())
//                        schoolNameContaining(search.getSchoolName()),
////                        schoolAddressContaining(search.getSchoolName())
//                        school.address.schoolAddress.in(search.getSchoolAddress())
//                )
//                .orderBy(school.createdDate.asc())
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//    }
    //    보육원 목록(최신순)(쿼리프로젝션 버전)
        @Override
        public List<SchoolDTO> findAllByCreatedDate(Pageable pageable, Search search) {
            return jpaQueryFactory.select(new QSchoolDTO(
                    school.schoolName,
                    school.address.schoolZipcode,
                    school.address.schoolAddress,
                    school.address.schoolAddressDetail,
                    school.schoolTeachers,
                    school.schoolKids,
                    school.schoolBudget,
                    school.schoolBank,
                    school.schoolAccount,
                    school.schoolPhoneNumber,
                    school.schoolQR,
                    school.schoolName,
                    school.schoolName,
                    school.userEmail,
                    school.userName,
                    school.userPassword,
                    school.userPhoneNumber,
                    school.donationCount
            ))
                    .where(
                            schoolNameContaining(search.getSchoolName()),
                            schoolAddressContaining(search.getSchoolAddress())
//                        밑에 코드 값 안넘겨주면 where 1 = 2 나와서 값을 제대로 안가져오는 문제 발견해서 위에 코드로 바꿈
//                        school.address.schoolAddress.in(search.getSchoolAddress())
//                                .distinct() = join했을 시 중복값 제거
                    ).from(school)
                    .orderBy(school.createdDate.asc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();
        }

    //
    private BooleanExpression schoolNameContaining(String schoolName) {
        return StringUtils.hasText(schoolName) ? school.schoolName.contains(schoolName) : null;
    }

    private BooleanExpression schoolAddressContaining(List<String> schoolAddress) {
//        return StringUtils.hasText(schoolAddress.get(0)) ? school.address.schoolAddress.in(schoolAddress) : null;
        return schoolAddress.size()>0 ? school.address.schoolAddress.in(schoolAddress) : null;
    }


}
