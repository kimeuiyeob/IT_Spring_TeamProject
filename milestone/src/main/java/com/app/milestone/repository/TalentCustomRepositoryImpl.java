package com.app.milestone.repository;

import com.app.milestone.domain.QTalentDTO;
import com.app.milestone.domain.Search;
import com.app.milestone.domain.TalentDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.app.milestone.entity.QDonation.donation;
import static com.app.milestone.entity.QPeople.people;
import static com.app.milestone.entity.QTalent.talent;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TalentCustomRepositoryImpl implements TalentCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    //동적쿼리
    // 재능기부 목록 (가능일자 ASC)
    @Override
    public List<TalentDTO> findAllByTalentAbleDate(Pageable pageable, Search search) {

        //pageale쓰면 pageale.of(파라미터 2개를 받을수있다) 첫번째가 현재페이지(page), 두번째가 페이지 사이즈(amount)
        return jpaQueryFactory.select(new QTalentDTO(
                talent.donationId,
                talent.talentTitle,
                talent.talentContent,
                talent.talentAbleDate,
                talent.talentCategory,
                talent.talentPlace,
                talent.people.peopleNickname,
                talent.school.userId,
                talent.people.userId
        ))
                .where(
                        talentTitleContaining(search.getTalentTitle()),
                        talentCategoryContaining(search.getTalentCategory()),
                        talentPlaceContaining(search.getTalentPlace())
                )
                .from(talent)
                .orderBy(talent.talentAbleDate.asc())
//                .offset(pageable.getOffset()) //여기서부터 10개를 가져온다 <-10개는 아래 limit ex)2페이지면 10부터 10개
//                .limit(pageable.getPageSize()) //목록에 뿌릴갯수 -> 10을보냈으니까 10으로 limit걸어논거다.
                .fetch();
    }

    //    조건에 따른 보육원 수
    @Override
    public Long countByAbleDate(Pageable pageable, Search search) {
        return jpaQueryFactory.select(talent.count())
                .from(talent)
                .where(
//                        보육원 이름 검색
                        talentTitleContaining(search.getTalentTitle()),
                        talentCategoryContaining(search.getTalentCategory()),
                        talentPlaceContaining(search.getTalentPlace())
                )
                .orderBy(talent.talentAbleDate.asc())
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
                .fetchOne();
    }


    //============제목 검색==============//
    private BooleanExpression talentTitleContaining(String talentTitle) { //ooleanExpression은 null 일때 무시될 수 있고, and또는 or절을 통해서 조합을 할 수 있다.
        return StringUtils.hasText(talentTitle) ? talent.talentTitle.contains(talentTitle) : null;
    } //ooleanExpression 조건문을 반환한다, null일때 메소드 사라진다.
    //talentTitle에값이 있다면  talent.talentTitle.contains(talentTitle) 이걸 where절에 반환하고 false면 null을 반환해서 where절의 talentTitleContaining 메소드를 삭제한다.

    //============카테고리 검색===========//
    private BooleanExpression talentCategoryContaining(String talentCategory) {
        return StringUtils.hasText(talentCategory) ? talent.talentCategory.contains(talentCategory) : null;
    }

    //============지역 검색==============//
    private BooleanBuilder talentPlaceContaining(List<String> talentPlace) {
        if (talentPlace.get(0) == null) {
            return null;
        }
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        for (String schoolAddress : talentPlace) {
            if (schoolAddress.equals("서울")) {
                booleanBuilder.or(talent.talentPlace.like("서울%"));
            }
            if (schoolAddress.equals("인천")) {
                booleanBuilder.or(talent.talentPlace.like("인천%"));
            }
            if (schoolAddress.equals("경기도")) {
                booleanBuilder.or(talent.talentPlace.like("경기%"));
            }
            if (schoolAddress.equals("강원도")) {
                booleanBuilder.or(talent.talentPlace.like("강원%"));
            }
            if (schoolAddress.equals("충청도")) {
                booleanBuilder.or(talent.talentPlace.like("충북%").or(talent.talentPlace.like("충남%")).or(talent.talentPlace.like("세종%")).or(talent.talentPlace.like("대전%")));
            }
            if (schoolAddress.equals("전라도")) {
                booleanBuilder.or(talent.talentPlace.like("전북%").or(talent.talentPlace.like("전남%")).or(talent.talentPlace.like("광주%")));
            }
            if (schoolAddress.equals("경상도")) {
                booleanBuilder.or(talent.talentPlace.like("경북%").or(talent.talentPlace.like("경남%")).or(talent.talentPlace.like("부산%")).or(talent.talentPlace.like("울산%")).or(talent.talentPlace.like("대구%")));
            }
            if (schoolAddress.equals("제주도")) {
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


    @Override
    public List<TalentDTO> talentDetail(Long userId) {
        return jpaQueryFactory.select(new QTalentDTO(
                talent.donationId,
                talent.talentTitle,
                talent.talentContent,
                talent.talentAbleDate,
                talent.talentCategory,
                talent.talentPlace,
                talent.people.peopleNickname,
                talent.school.userId,
                talent.people.userId
        )).from(talent, donation, people)
                .where(talent.donationId.eq(donation.donationId))
                .where(donation.people.userId.eq(people.userId))
                .where(people.userId.eq(userId))
                .fetch();
    }

    /*=========================카테고리 AJAX==============================*/

    @Override //전체 리스트 조회
    public List<TalentDTO> allList() {
        return jpaQueryFactory.select(new QTalentDTO(
                talent.donationId,
                talent.talentTitle,
                talent.talentContent,
                talent.talentAbleDate,
                talent.talentCategory,
                talent.talentPlace,
                talent.people.peopleNickname,
                talent.school.userId,
                talent.people.userId
        ))
                .from(talent)
                .orderBy(talent.talentAbleDate.asc())
                .fetch();
    }

    @Override //교육 리스트 조회
    public List<TalentDTO> educationList() {
        return jpaQueryFactory.select(new QTalentDTO(
                talent.donationId,
                talent.talentTitle,
                talent.talentContent,
                talent.talentAbleDate,
                talent.talentCategory,
                talent.talentPlace,
                talent.people.peopleNickname,
                talent.school.userId,
                talent.people.userId
        ))
                .from(talent)
                .where(talent.talentCategory.eq("교육"))
                .orderBy(talent.talentAbleDate.asc())
                .fetch();
    }

    @Override //운동 리스트 조회
    public List<TalentDTO> exerciseList() {
        return jpaQueryFactory.select(new QTalentDTO(
                talent.donationId,
                talent.talentTitle,
                talent.talentContent,
                talent.talentAbleDate,
                talent.talentCategory,
                talent.talentPlace,
                talent.people.peopleNickname,
                talent.school.userId,
                talent.people.userId
        )).from(talent)
                .where(talent.talentCategory.eq("운동"))
                .orderBy(talent.talentAbleDate.asc())
                .fetch();
    }

    @Override //음악 리스트 조회
    public List<TalentDTO> musicList() {
        return jpaQueryFactory.select(new QTalentDTO(
                talent.donationId,
                talent.talentTitle,
                talent.talentContent,
                talent.talentAbleDate,
                talent.talentCategory,
                talent.talentPlace,
                talent.people.peopleNickname,
                talent.school.userId,
                talent.people.userId
        )).from(talent)
                .where(talent.talentCategory.eq("음악"))
                .orderBy(talent.talentAbleDate.asc())
                .fetch();
    }

    @Override //미술 리스트 조회
    public List<TalentDTO> artList() {
        return jpaQueryFactory.select(new QTalentDTO(
                talent.donationId,
                talent.talentTitle,
                talent.talentContent,
                talent.talentAbleDate,
                talent.talentCategory,
                talent.talentPlace,
                talent.people.peopleNickname,
                talent.school.userId,
                talent.people.userId
        )).from(talent)
                .where(talent.talentCategory.eq("미술"))
                .orderBy(talent.talentAbleDate.asc())
                .fetch();
    }

    @Override //IT 리스트 조회
    public List<TalentDTO> itList() {
        return jpaQueryFactory.select(new QTalentDTO(
                talent.donationId,
                talent.talentTitle,
                talent.talentContent,
                talent.talentAbleDate,
                talent.talentCategory,
                talent.talentPlace,
                talent.people.peopleNickname,
                talent.school.userId,
                talent.people.userId
        )).from(talent)
                .where(talent.talentCategory.eq("IT"))
                .orderBy(talent.talentAbleDate.asc())
                .fetch();
    }

    /*===================================================================*/

}
