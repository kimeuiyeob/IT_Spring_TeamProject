package com.app.milestone.repository;

import com.app.milestone.domain.QTalentDTO;
import com.app.milestone.domain.Search;
import com.app.milestone.domain.TalentDTO;
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

import static com.app.milestone.entity.QDonation.donation;
import static com.app.milestone.entity.QFile.file;
import static com.app.milestone.entity.QPeople.people;
import static com.app.milestone.entity.QTalent.talent;
import static com.app.milestone.entity.QUser.user;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TalentCustomRepositoryImpl implements TalentCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    /*=============================================================================================================*/
    // 재능기부 목록 (가능일자 ASC)
    @Override
    public List<TalentDTO> findAllByTalentAbleDate(Pageable pageable, Search search) {
        //pageale쓰면 pageale.of(파라미터 2개를 받을수있다) 첫번째가 현재페이지(page), 두번째가 페이지 사이즈(amount)
        return jpaQueryFactory.select(new QTalentDTO(
                talent.donationId,
                talent.talentTitle,
                talent.talentContent,
                talent.talentAbleDate,
                talent.createdDate,
                talent.talentCategory,
                talent.talentPlace,
                talent.people.peopleNickname,
                talent.school.userId,
                talent.people.userId,
                talent.people.userName,
                talent.people.userEmail,
                file.fileName,
                file.filePath,
                file.fileUuid,
                file.fileSize,
                file.fileImageCheck,
                file.fileType
        ))
                .from(talent)
                .leftJoin(file)
                .on((file.user.userId.eq(talent.people.userId)))
                .where(
                        talentTitleContaining(search.getTalentTitle()),
                        talentPlaceContaining(search.getTalentPlace()),
                        talentCategoryContainingAll(search.getTalentCategory()) //카테고리 클릭시
                )
                .where(talent.school.userId.isNull())
                .orderBy(talent.talentAbleDate.asc())
                .offset(pageable.getOffset()) //여기서부터 10개를 가져온다 <-10개는 아래 limit ex)2페이지면 10부터 10개
                .limit(pageable.getPageSize()) //목록에 뿌릴갯수 -> 10을보냈으니까 10으로 limit걸어논거다.
                .fetch();
    }


    /*=============================================================================================================*/

    //마이페이지 나의 재능기부 목록
    @Override
    public List<TalentDTO> findAllTalentById(Pageable pageable, Long peopleId) {
        //pageale쓰면 pageale.of(파라미터 2개를 받을수있다) 첫번째가 현재페이지(page), 두번째가 페이지 사이즈(amount)
        return jpaQueryFactory.select(new QTalentDTO(
                talent.donationId,
                talent.talentTitle,
                talent.talentContent,
                talent.talentAbleDate,
                talent.createdDate,
                talent.talentCategory,
                talent.talentPlace,
                talent.people.peopleNickname,
                talent.school.userId,
                talent.people.userId,
                talent.people.userName,
                talent.people.userEmail,
                file.fileName,
                file.filePath,
                file.fileUuid,
                file.fileSize,
                file.fileImageCheck,
                file.fileType
        ))
                .where(talent.people.userId.eq(peopleId))
                .from(talent)
                .leftJoin(file)
                .on((file.user.userId.eq(talent.people.userId)))
                .orderBy(talent.talentAbleDate.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }


    @Override
    public List<TalentDTO> findByDonationId(Long donationId) {
        return jpaQueryFactory.select(new QTalentDTO(
                talent.donationId,
                talent.talentTitle,
                talent.talentContent,
                talent.talentAbleDate,
                talent.createdDate,
                talent.talentCategory,
                talent.talentPlace,
                talent.people.peopleNickname,
                talent.school.userId,
                talent.people.userId,
                talent.people.userName,
                talent.people.userEmail
        ))
                .where(talent.donationId.eq(donationId))
                .from(talent)
                .fetch();
    }


    /*=============================================================================================================*/
    //조건에 따른 보육원 수
    @Override
    public Long countByAbleDate(Pageable pageable, Search search) {
        return jpaQueryFactory.select(talent.count())
                .from(talent)
                .where(
//                        보육원 이름 검색
                        talentTitleContaining(search.getTalentTitle()),
                        talentPlaceContaining(search.getTalentPlace()),
                        talentCategoryContainingAll(search.getTalentCategory())
                )
                .orderBy(talent.talentAbleDate.asc())
                .fetchOne();
    }
    /*=============================================================================================================*/
    //재능기부 목록 페이징
    @Override
    public Long countByAbleDate3(Pageable pageable, Search search) {
        return jpaQueryFactory.select(talent.count())
                .from(talent)
                .where(
//                        보육원 이름 검색
                        talentTitleContaining(search.getTalentTitle()),
                        talentPlaceContaining(search.getTalentPlace()),
                        talentCategoryContainingAll(search.getTalentCategory())
                )
//                .where(talent.school.userId.isNotNull().and(talent.people.userId.isNotNull()))
                .where(talent.school.userId.isNull())
                .orderBy(talent.talentAbleDate.asc())
                .fetchOne();
    }

    /*=============================================================================================================*/
    //조건에 따른 보육원 수
    @Override
    public Long countByAbleDate2(Pageable pageable, Long peopleId) {
        return jpaQueryFactory.select(talent.count())
                .from(talent)
                .where(talent.people.userId.eq(peopleId))
                .orderBy(talent.talentAbleDate.asc())
                .fetchOne();
    }


    /*=============================================================================================================*/
    //  재능기부 랭킹 정렬
    @Override
    public List<Tuple> sortBytalentRank() {
        List<Tuple> tuples = new ArrayList<>();
        Tuple temp = null;

        tuples = jpaQueryFactory.select(talent.talentAbleDate.count(), talent.people.userId)
                .from(talent)
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

    /*===============================================================================================================================================================================*/
    @Override  //게시글 상세보기
    public List<TalentDTO> talentDetail(Long donationId) {
        return jpaQueryFactory.select(new QTalentDTO(
                talent.donationId,
                talent.talentTitle,
                talent.talentContent,
                talent.talentAbleDate,
                talent.createdDate,
                talent.talentCategory,
                talent.talentPlace,
                talent.people.peopleNickname,
                talent.school.userId,
                talent.people.userId,
                talent.people.userName,
                talent.people.userEmail,
                file.fileName,
                file.filePath,
                file.fileUuid,
                file.fileSize,
                file.fileImageCheck,
                file.fileType
        ))
                .from(talent)
                .leftJoin(file)
                .on((file.user.userId.eq(talent.people.userId)))
                .where(talent.donationId.eq(donationId))
                .fetch();
    }


    //============제목 검색==============//
    private BooleanExpression talentTitleContaining(String talentTitle) { //booleanExpression은 null 일때 무시될 수 있고, and또는 or절을 통해서 조합을 할 수 있다.
        return StringUtils.hasText(talentTitle) ? talent.talentTitle.contains(talentTitle) : null;
    } //booleanExpression 조건문을 반환한다, null일때 메소드 사라진다.
    //talentTitle에값이 있다면  talent.talentTitle.contains(talentTitle) 이걸 where절에 반환하고 false면 null을 반환해서 where절의 talentTitleContaining 메소드를 삭제한다.


    //============카테고리 전체===========//
    private BooleanBuilder talentCategoryContainingAll(String talentCategory) {
        if (talentCategory == null) {
            return null;
        }
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (talentCategory.equals("전체")) {
            booleanBuilder.or(talent.talentCategory.like("교육%"));
            booleanBuilder.or(talent.talentCategory.like("운동%"));
            booleanBuilder.or(talent.talentCategory.like("음악%"));
            booleanBuilder.or(talent.talentCategory.like("미술%"));
            booleanBuilder.or(talent.talentCategory.like("IT%"));
        }
        if (talentCategory.equals("교육")) {
            booleanBuilder.or(talent.talentCategory.like("교육%"));
        }
        if (talentCategory.equals("운동")) {
            booleanBuilder.or(talent.talentCategory.like("운동%"));
        }
        if (talentCategory.equals("음악")) {
            booleanBuilder.or(talent.talentCategory.like("음악%"));
        }
        if (talentCategory.equals("미술")) {
            booleanBuilder.or(talent.talentCategory.like("미술%"));
        }
        if (talentCategory.equals("IT")) {
            booleanBuilder.or(talent.talentCategory.like("IT%"));
        }
        return booleanBuilder;
    }

    //============지역 검색==============//
    private BooleanBuilder talentPlaceContaining(List<String> talentPlace) {
        if (talentPlace == null) {
            return null;
        }
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        for (String schoolAddress : talentPlace) {
            if (schoolAddress.contains("서울")) {
                booleanBuilder.or(talent.talentPlace.like("서울%"));
            }
            if (schoolAddress.contains("인천")) {
                booleanBuilder.or(talent.talentPlace.like("인천%"));
            }
            if (schoolAddress.contains("경기도")) {
                booleanBuilder.or(talent.talentPlace.like("경기%"));
            }
            if (schoolAddress.contains("강원도")) {
                booleanBuilder.or(talent.talentPlace.like("강원%"));
            }
            if (schoolAddress.contains("충청도")) {
                booleanBuilder.or(talent.talentPlace.like("충청%"));
            }
            if (schoolAddress.contains("전라도")) {
                booleanBuilder.or(talent.talentPlace.like("전라%"));
            }
            if (schoolAddress.contains("경상도")) {
                booleanBuilder.or(talent.talentPlace.like("경상%"));
            }
            if (schoolAddress.contains("제주도")) {
                booleanBuilder.or(talent.talentPlace.like("제주%"));
            }
        }
        return booleanBuilder;
    }


//          =========페이징 처리===========
//          .offset(0) == 0부터 시작한다.
//          .limit(10) == 10개 가져온다.
//          Page<User> userPaging = new UserImpl<> (users, PageRequest.of(page0,amount10),total);
//          userPaging.getTotalPages(); == 페이지 전부
//          userPaging.isFirst(); == 첫번째 페이지
//          userPaging.getContent(); == 1페이지에 있는 user List
//          =============================

    /*=================================================================================================*/
    /*=================================================================================================*/


    /* 관리자 페이지=============================================================*/
    @Override
    public Long countByCreatedDate(Pageable pageable, Search search) {
        return jpaQueryFactory.select(talent.count())
                .from(talent)
                .where(
                        peopleNameAndEmailAndNicknameAndCategoryAndPlace(search.getKeyword()),
                        peopleNameAndEmailAndNicknameAndCategoryAndPlace2(search.getTalentCategory())
//                        peopleNameAndEmailAndNicknameAndCategoryAndPlace3(search.getTalentPlaceOne())
                )
                .orderBy(talent.createdDate.asc())
                .fetchOne();
    }

    // 통합검색 및 카테고리
    private BooleanBuilder peopleNameAndEmailAndNicknameAndCategoryAndPlace(String keyword) {
        if (keyword == null) {
            return null;
        }
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (keyword != null) {
            booleanBuilder.or(talent.people.userName.contains(keyword));
            booleanBuilder.or(talent.people.userEmail.contains(keyword));
            booleanBuilder.or(talent.people.peopleNickname.contains(keyword));
            booleanBuilder.or(talent.talentCategory.contains(keyword));
            booleanBuilder.or(talent.talentPlace.contains(keyword));
        }
        return booleanBuilder;
    }

    private BooleanBuilder peopleNameAndEmailAndNicknameAndCategoryAndPlace2(String talentCategory) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (talentCategory == null) {
            return null;
        }

        if (talentCategory != null) {
            booleanBuilder.or(talent.talentCategory.contains(talentCategory));
        }

        return booleanBuilder;
    }

    public List<TalentDTO> findTalentSearch(Pageable pageable, Search search) {
        return jpaQueryFactory.select(new QTalentDTO(
                talent.donationId,
                talent.talentTitle,
                talent.talentContent,
                talent.talentAbleDate,
                talent.createdDate,
                talent.talentCategory,
                talent.talentPlace,
                talent.people.peopleNickname,
                talent.school.userId,
                talent.people.userId,
                talent.people.userName,
                talent.people.userEmail
        ))
                .from(talent, donation, people, user)
                .where(
                        talent.donationId.eq(donation.donationId),
                        donation.people.userId.eq(people.userId),
                        people.userId.eq(user.userId),
                        peopleNameAndEmailAndNicknameAndCategoryAndPlace(search.getKeyword()),
                        peopleNameAndEmailAndNicknameAndCategoryAndPlace2(search.getTalentCategory())
//                        peopleNameAndEmailAndNicknameAndCategoryAndPlace3(search.getTalentPlaceOne())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(talent.createdDate.desc())
                .fetch();
    }

    ;


}
