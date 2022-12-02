package com.app.milestone.repository;

import com.app.milestone.domain.QSchoolDTO;
import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.domain.Search;
import com.app.milestone.entity.QSchool;
import com.app.milestone.entity.School;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.app.milestone.entity.QSchool.school;

@Repository
@RequiredArgsConstructor
@Slf4j
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
                school.introduce.schoolTitle,
                school.introduce.schoolContent,
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
                school.address.schoolAddress,
                school.address.schoolAddressDetail,
                school.address.schoolZipcode,
                school.schoolTeachers,
                school.schoolKids,
                school.schoolBudget,
                school.schoolBank,
                school.schoolAccount,
                school.schoolPhoneNumber,
                school.schoolQR,
                school.introduce.schoolTitle,
                school.introduce.schoolContent,
                school.userEmail,
                school.userName,
                school.userPassword,
                school.userPhoneNumber,
                school.donationCount
        ))
                .where(
//                        보육원 이름 검색
                        schoolNameContaining(search.getSchoolName()),
//                        schoolAddressContaining(search.getSchoolAddress())
//                        밑에 코드 값 안넘겨주면 where 1 = 2 나와서 값을 제대로 안가져오는 문제 발견해서 위에 코드로 바꿈
//                        school.address.schoolAddress.in(search.getSchoolAddress())
//                                .distinct() = join했을 시 중복값 제거

//                        지역선택
                        schoolAddressContaining1(search.getSchoolAddress().get(0)),
                        schoolAddressContaining2(search.getSchoolAddress().get(1)),
                        schoolAddressContaining3(search.getSchoolAddress().get(2)),
                        schoolAddressContaining4(search.getSchoolAddress().get(3)),
                        schoolAddressContaining5(search.getSchoolAddress().get(4)),
                        schoolAddressContaining6(search.getSchoolAddress().get(5)),
                        schoolAddressContaining7(search.getSchoolAddress().get(6)),
                        schoolAddressContaining8(search.getSchoolAddress().get(7))
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

//    private BooleanExpression schoolAddressContaining(List<String> schoolAddress) {
//        return schoolAddress.size() > 0 ? school.address.schoolAddress.in(schoolAddress) : null;
//    }

    private BooleanExpression schoolAddressContaining1(String schoolAddress) {
        if (!StringUtils.hasText(schoolAddress)) return null;
        if (schoolAddress.equals("서울")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("서울") : null;
        }
        if (schoolAddress.equals("인천")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("인천") : null;
        }
        if (schoolAddress.equals("경기도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("경기") : null;
        }
        if (schoolAddress.equals("강원도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("강원") : null;
        }
        if (schoolAddress.equals("충청도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("충북").or(school.address.schoolAddress.like("충남")).or(school.address.schoolAddress.like("세종")).or(school.address.schoolAddress.like("대전"))
                    : null;
        }
        if (schoolAddress.equals("전라도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("전북").or(school.address.schoolAddress.like("전남")).or(school.address.schoolAddress.like("광주")) : null;
        }
        if (schoolAddress.equals("경상도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("경북").or(school.address.schoolAddress.like("경남")).or(school.address.schoolAddress.like("부산")).or(school.address.schoolAddress.like("울산")).or(school.address.schoolAddress.like("대구")) : null;
        }
        if (schoolAddress.equals("제주도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("제주") : null;
        }
        return null;
    }

    private BooleanExpression schoolAddressContaining2(String schoolAddress) {
        if (!StringUtils.hasText(schoolAddress)) return null;
        if (schoolAddress.equals("서울")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("서울") : null;
        }
        if (schoolAddress.equals("인천")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("인천") : null;
        }
        if (schoolAddress.equals("경기도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("경기") : null;
        }
        if (schoolAddress.equals("강원도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("강원") : null;
        }
        if (schoolAddress.equals("충청도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("충북").or(school.address.schoolAddress.like("충남")).or(school.address.schoolAddress.like("세종")).or(school.address.schoolAddress.like("대전"))
                    : null;
        }
        if (schoolAddress.equals("전라도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("전북").or(school.address.schoolAddress.like("전남")).or(school.address.schoolAddress.like("광주")) : null;
        }
        if (schoolAddress.equals("경상도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("경북").or(school.address.schoolAddress.like("경남")).or(school.address.schoolAddress.like("부산")).or(school.address.schoolAddress.like("울산")).or(school.address.schoolAddress.like("대구")) : null;
        }
        if (schoolAddress.equals("제주도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("제주") : null;
        }
        return null;
    }

    private BooleanExpression schoolAddressContaining3(String schoolAddress) {
        if (!StringUtils.hasText(schoolAddress)) return null;
        if (schoolAddress.equals("서울")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("서울") : null;
        }
        if (schoolAddress.equals("인천")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("인천") : null;
        }
        if (schoolAddress.equals("경기도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("경기") : null;
        }
        if (schoolAddress.equals("강원도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("강원") : null;
        }
        if (schoolAddress.equals("충청도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("충북").or(school.address.schoolAddress.like("충남")).or(school.address.schoolAddress.like("세종")).or(school.address.schoolAddress.like("대전"))
                    : null;
        }
        if (schoolAddress.equals("전라도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("전북").or(school.address.schoolAddress.like("전남")).or(school.address.schoolAddress.like("광주")) : null;
        }
        if (schoolAddress.equals("경상도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("경북").or(school.address.schoolAddress.like("경남")).or(school.address.schoolAddress.like("부산")).or(school.address.schoolAddress.like("울산")).or(school.address.schoolAddress.like("대구")) : null;
        }
        if (schoolAddress.equals("제주도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("제주") : null;
        }
        return null;
    }

    private BooleanExpression schoolAddressContaining4(String schoolAddress) {
        if (!StringUtils.hasText(schoolAddress)) return null;
        if (schoolAddress.equals("서울")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("서울") : null;
        }
        if (schoolAddress.equals("인천")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("인천") : null;
        }
        if (schoolAddress.equals("경기도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("경기") : null;
        }
        if (schoolAddress.equals("강원도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("강원") : null;
        }
        if (schoolAddress.equals("충청도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("충북").or(school.address.schoolAddress.like("충남")).or(school.address.schoolAddress.like("세종")).or(school.address.schoolAddress.like("대전"))
                    : null;
        }
        if (schoolAddress.equals("전라도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("전북").or(school.address.schoolAddress.like("전남")).or(school.address.schoolAddress.like("광주")) : null;
        }
        if (schoolAddress.equals("경상도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("경북").or(school.address.schoolAddress.like("경남")).or(school.address.schoolAddress.like("부산")).or(school.address.schoolAddress.like("울산")).or(school.address.schoolAddress.like("대구")) : null;
        }
        if (schoolAddress.equals("제주도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("제주") : null;
        }
        return null;
    }

    private BooleanExpression schoolAddressContaining5(String schoolAddress) {
        if (!StringUtils.hasText(schoolAddress)) return null;
        if (schoolAddress.equals("서울")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("서울") : null;
        }
        if (schoolAddress.equals("인천")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("인천") : null;
        }
        if (schoolAddress.equals("경기도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("경기") : null;
        }
        if (schoolAddress.equals("강원도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("강원") : null;
        }
        if (schoolAddress.equals("충청도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("충북").or(school.address.schoolAddress.like("충남")).or(school.address.schoolAddress.like("세종")).or(school.address.schoolAddress.like("대전"))
                    : null;
        }
        if (schoolAddress.equals("전라도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("전북").or(school.address.schoolAddress.like("전남")).or(school.address.schoolAddress.like("광주")) : null;
        }
        if (schoolAddress.equals("경상도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("경북").or(school.address.schoolAddress.like("경남")).or(school.address.schoolAddress.like("부산")).or(school.address.schoolAddress.like("울산")).or(school.address.schoolAddress.like("대구")) : null;
        }
        if (schoolAddress.equals("제주도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("제주") : null;
        }
        return null;
    }

    private BooleanExpression schoolAddressContaining6(String schoolAddress) {
        if (!StringUtils.hasText(schoolAddress)) return null;
        if (schoolAddress.equals("서울")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("서울") : null;
        }
        if (schoolAddress.equals("인천")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("인천") : null;
        }
        if (schoolAddress.equals("경기도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("경기") : null;
        }
        if (schoolAddress.equals("강원도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("강원") : null;
        }
        if (schoolAddress.equals("충청도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("충북").or(school.address.schoolAddress.like("충남")).or(school.address.schoolAddress.like("세종")).or(school.address.schoolAddress.like("대전"))
                    : null;
        }
        if (schoolAddress.equals("전라도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("전북").or(school.address.schoolAddress.like("전남")).or(school.address.schoolAddress.like("광주")) : null;
        }
        if (schoolAddress.equals("경상도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("경북").or(school.address.schoolAddress.like("경남")).or(school.address.schoolAddress.like("부산")).or(school.address.schoolAddress.like("울산")).or(school.address.schoolAddress.like("대구")) : null;
        }
        if (schoolAddress.equals("제주도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("제주") : null;
        }
        return null;
    }

    private BooleanExpression schoolAddressContaining7(String schoolAddress) {
        if (!StringUtils.hasText(schoolAddress)) return null;
        if (schoolAddress.equals("서울")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("서울") : null;
        }
        if (schoolAddress.equals("인천")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("인천") : null;
        }
        if (schoolAddress.equals("경기도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("경기") : null;
        }
        if (schoolAddress.equals("강원도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("강원") : null;
        }
        if (schoolAddress.equals("충청도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("충북").or(school.address.schoolAddress.like("충남")).or(school.address.schoolAddress.like("세종")).or(school.address.schoolAddress.like("대전"))
                    : null;
        }
        if (schoolAddress.equals("전라도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("전북").or(school.address.schoolAddress.like("전남")).or(school.address.schoolAddress.like("광주")) : null;
        }
        if (schoolAddress.equals("경상도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("경북").or(school.address.schoolAddress.like("경남")).or(school.address.schoolAddress.like("부산")).or(school.address.schoolAddress.like("울산")).or(school.address.schoolAddress.like("대구")) : null;
        }
        if (schoolAddress.equals("제주도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("제주") : null;
        }
        return null;
    }

    private BooleanExpression schoolAddressContaining8(String schoolAddress) {
        if (!StringUtils.hasText(schoolAddress)) return null;
        if (schoolAddress.equals("서울")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("서울") : null;
        }
        if (schoolAddress.equals("인천")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("인천") : null;
        }
        if (schoolAddress.equals("경기도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("경기") : null;
        }
        if (schoolAddress.equals("강원도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("강원") : null;
        }
        if (schoolAddress.equals("충청도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("충북").or(school.address.schoolAddress.like("충남")).or(school.address.schoolAddress.like("세종")).or(school.address.schoolAddress.like("대전"))
                    : null;
        }
        if (schoolAddress.equals("전라도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("전북").or(school.address.schoolAddress.like("전남")).or(school.address.schoolAddress.like("광주")) : null;
        }
        if (schoolAddress.equals("경상도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("경북").or(school.address.schoolAddress.like("경남")).or(school.address.schoolAddress.like("부산")).or(school.address.schoolAddress.like("울산")).or(school.address.schoolAddress.like("대구")) : null;
        }
        if (schoolAddress.equals("제주도")) {
            return StringUtils.hasText(schoolAddress) ? school.address.schoolAddress.like("제주") : null;
        }
        return null;
    }

}
