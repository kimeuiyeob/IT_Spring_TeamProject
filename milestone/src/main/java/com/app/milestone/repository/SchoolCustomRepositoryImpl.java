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

    //====================황지수====================
    //  메인 도움이 필요해요(기부받은 횟수가 적은 수)
    //  QR코드를 기준으로 등록된 보육원을 도움받은 횟수가 적을 순으로 정렬했습니다.
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
                .where(
                        school.schoolQR.isNotNull()
                )
                .orderBy(school.donationCount.asc())
                .offset(0)
                .limit(5)
                .fetch();
    }

    //====================황지수====================
    //    보육원 목록(최신순)
    //  QR코드를 기준으로 등록된 보육원을 최신 등록순으로 정렬했습니다.
    //  동적쿼리를 사용하여 여러 지역을 선택하거나 보육원이름을 직접검색했을 때에 유동적으로 쿼리가 바뀔 수 있게 구현했습니다.
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
                        school.schoolQR.isNotNull(),
                        schoolNameContaining(search.getSchoolName()),
                        schoolAddressContaining(search.getSchoolAddress())
                ).from(school)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(school.updatedDate.desc())
                .fetch();
    }

    //====================황지수====================
    //    조건에 따른 보육원 수
    //  List타입을 Page타입으로 바꾸게 될 때 총개수가 필요한데 그 때 사용하게 될 메소드입니다.
    //  위의 메소드와 마찬가지로 동적쿼리로 여러지역과 보육원이름으로 조회하게 했습니다.
    @Override
    public Long countByCreatedDate(Pageable pageable, Search search) {
        return jpaQueryFactory.select(school.count())
                .from(school)
                .where(
//                        보육원 이름 검색
                        school.schoolQR.isNotNull(),
                        schoolNameContaining(search.getSchoolName()),
                        schoolAddressContaining(search.getSchoolAddress())
                )
                .orderBy(school.createdDate.asc())
                .fetchOne();
    }

    //====================황지수====================
    //    보육원 정보(하나)
    //  보육원의 아이디를 통하여 해당 보육원의 정보를 조회합니다.
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

//
    //    User검색
    private BooleanExpression userNameContaining(String userName) {
        return StringUtils.hasText(userName) ? school.userName.contains(userName) : null;
    }

    //====================황지수====================
    //    SchoolName 검색
    //  동적쿼리에 사용될 메소드입니다. 파라미터로 전달한 값이 있다면 조건문을 아니라면 null을 반환합니다.
    private BooleanExpression schoolNameContaining(String schoolName) {
        return StringUtils.hasText(schoolName) ? school.schoolName.contains(schoolName) : null;
    }

    //====================황지수====================
    //    지역검색
    //  서울, 인천, 경기도, 강원도, 충청도, 전라도, 경상도, 제주도로 지역을 검색하게 되는데
    //  충청도일 경우 충북과 충남이 들어가고 지역쪽에 위치한 시들도 포함하여 검색하게 하였습니다.
    //  BooleanBuiler를 사용하여 조건에 맞는 조건문을 쌓아가며 리턴했습니다.
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


    //========================관리자페이지===========================
    @Override
    public List<School> findByCreatedDate(Pageable pageable) {
        return jpaQueryFactory.selectFrom(school)
                .orderBy(school.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    //========================관리자페이지===========================

    //    통합검색 보육원 수2
    @Override
    public Long countByCreatedDate2(Pageable pageable, String keyword) {
        return jpaQueryFactory.select(school.count())
                .from(school)
                .where(
                        userNameAndSchoolNameContaining(keyword)
                )
                .orderBy(school.createdDate.asc())
                .fetchOne();
    }

    //    통합검색 보육원 수3
    @Override
    public Long countByCreatedDate3(Pageable pageable, String keyword) {
        return jpaQueryFactory.select(school.count())
                .from(school)
                .where(
                        schoolNameAndAddressContaining(keyword)
                )
                .orderBy(school.createdDate.asc())
                .fetchOne();
    }

    //    예산 순 내림차순, 검색
    @Override
    public List<SchoolDTO> findByBudgetAndSearch(Pageable pageable, String keyword) {
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
                        schoolNameAndAddressContaining(keyword)
                )
                .offset(pageable.getOffset()) //여기서부터 10개를 가져온다 <-10개는 아래 limit ex)2페이지면 10부터 10개
                .limit(pageable.getPageSize()) //목록에 뿌릴갯수 -> 10을보냈으니까 10으로 limit걸어논거다.
                .orderBy(school.schoolBudget.desc())
                .fetch();
    }

    //    예산 순 오름차순, 검색
    @Override
    public List<SchoolDTO> findByBudgetAndSearchAsc(Pageable pageable, String keyword) {
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
                        schoolNameAndAddressContaining(keyword)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(school.schoolBudget.asc())
                .fetch();
    }

    public List<SchoolDTO> findSchoolSearch(Pageable pageable, String keyword) {
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
                        userNameAndSchoolNameContaining(keyword)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(school.createdDate.desc())
                .fetch();
    }

    ;

    public List<SchoolDTO> findSchoolSearchAsc(Pageable pageable, String keyword) {
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
                        userNameAndSchoolNameContaining(keyword)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(school.createdDate.asc())
                .fetch();
    }

    ;


    // 통합검색2
    private BooleanBuilder userNameAndSchoolNameContaining(String keyword) {
        if (keyword == null) {
            return null;
        }
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (keyword != null) {
            booleanBuilder.or(school.userName.contains(keyword));
            booleanBuilder.or(school.schoolName.contains(keyword));
        }
        return booleanBuilder;
    }

    // 통합검색3
    private BooleanBuilder schoolNameAndAddressContaining(String keyword) {
        if (keyword == null) {
            return null;
        }
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (keyword != null) {
            booleanBuilder.or(school.schoolName.contains(keyword));
            booleanBuilder.or(school.address.schoolAddress.contains(keyword));
        }
        return booleanBuilder;
    }


}
