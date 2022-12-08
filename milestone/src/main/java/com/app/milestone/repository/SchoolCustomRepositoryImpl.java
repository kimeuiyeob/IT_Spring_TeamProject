package com.app.milestone.repository;

import com.app.milestone.domain.QSchoolDTO;
import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.domain.Search;
import com.app.milestone.entity.QSchool;
import com.app.milestone.entity.School;
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

import static com.app.milestone.entity.QSchool.*;


@Repository
@RequiredArgsConstructor
@Slf4j
public class SchoolCustomRepositoryImpl implements SchoolCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    //  메인 도움이 필요해요
    @Override
    public List<SchoolDTO> findAllByDonationCount() {
        return jpaQueryFactory.select(new QSchoolDTO(
                school.userId,
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
                school.donationCount,
                school.createdDate

        )).from(school)
                .orderBy(school.donationCount.asc())
                .offset(0)
                .limit(5)
                .fetch();
    }

    //    보육원 목록(최신순)
    @Override
    public List<SchoolDTO> findAllByCreatedDate(Pageable pageable, Search search) {
        return jpaQueryFactory.select(new QSchoolDTO(
                school.userId,
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
                school.donationCount,
                school.createdDate
        ))
                .where(
                        schoolNameContaining(search.getSchoolName()),
                        schoolAddressContaining0(search.getSchoolAddress())
                ).from(school)
                .orderBy(school.createdDate.desc())
                .fetch();
    }

    //    보육원 정보(하나)
    @Override
    public SchoolDTO findByUserId(Long userId) {
        return jpaQueryFactory.select(new QSchoolDTO(
                school.userId,
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
                school.donationCount,
                school.createdDate
        )).from(school)
                .where(school.userId.eq(userId))
                .fetchOne();
    }

    //    조건에 따른 보육원 수
    @Override
    public Long countByCreatedDate(Pageable pageable, Search search) {
        return jpaQueryFactory.select(school.count())
                .from(school)
                .where(
//                        보육원 이름 검색
                        schoolNameContaining(search.getSchoolName()),
                        schoolAddressContaining0(search.getSchoolAddress())
                )
                .orderBy(school.createdDate.asc())
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
                .fetchOne();
    }

    //    이름검색
    private BooleanExpression schoolNameContaining(String schoolName) {
        return StringUtils.hasText(schoolName) ? school.schoolName.contains(schoolName) : null;
    }

    //    지역검색
    private BooleanBuilder schoolAddressContaining0(List<String> schoolAddresses) {
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


    //========================관리자페이지===========================
    //보육원 목록
    @Override
    public List<School> findByCreatedDate(Pageable pageable) {
        return jpaQueryFactory.selectFrom(school)
                .orderBy(school.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public List<SchoolDTO> findBySchoolOnly(Pageable pageable, Search search) {
        return jpaQueryFactory.select(new QSchoolDTO(
                school.userId,
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
                school.donationCount,
                school.createdDate
        ))
                .from(school)
                .where(
                        schoolNameContaining(search.getSchoolName()),
                        schoolAddressContaining0(search.getSchoolAddress())
                )
                .orderBy(school.createdDate.desc())
                .fetch();
    }


        @Override
        public List<SchoolDTO> findBySchoolOnlyAsc() {
            return jpaQueryFactory.select(new QSchoolDTO(
                    school.userId,
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
                    school.donationCount,
                    school.createdDate
            ))
                    .from(school)
                    .orderBy(school.createdDate.asc())
                    .fetch();
        }






/*====================================================================*/
/*====================================================================*/
/*====================================================================*/
/*====================================================================*/
/*====================================================================*/
    //    예산 순 내림차순, 검색(기본)
    @Override
    public List<SchoolDTO> findByBudgetAndSearch(Pageable pageable, Search search) {
        return jpaQueryFactory.select(new QSchoolDTO(
                school.userId,
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
                school.donationCount,
                school.createdDate
        ))
                .from(school)
                .where(
                        schoolNameContaining(search.getSchoolName()),
                        schoolAddressContaining0(search.getSchoolAddress())
                )
                .orderBy(school.schoolBudget.desc())
                .fetch();
    }

    //    예산 순 오름차순, 검색
    @Override
    public List<SchoolDTO> findByBudgetAndSearchAsc(Pageable pageable, Search search) {
        return jpaQueryFactory.select(new QSchoolDTO(
                school.userId,
                school.schoolName,
                school.address.schoolAddress,
                school.address.schoolAddressDetail,
                school.address.schoolZipcode,
                school.schoolTeachers,
                school.schoolKids,
                school.schoolBudget,
                school.schoolBank,
                school.schoolAccount,
                school.schoolQR,
                school.introduce.schoolTitle,
                school.introduce.schoolContent,
                school.userEmail,
                school.schoolPhoneNumber,
                school.userName,
                school.userPassword,
                school.userPhoneNumber,
                school.donationCount,
                school.createdDate
        ))
                .from(school)
                .where(
                        //                  보육원 이름 검색
                        schoolNameContaining(search.getSchoolName()),
                        schoolAddressContaining0(search.getSchoolAddress())
                )
                .orderBy(school.schoolBudget.asc())
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
                .fetch();
    }




}
