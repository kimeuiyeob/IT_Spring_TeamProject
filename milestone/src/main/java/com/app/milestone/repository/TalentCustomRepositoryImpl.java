package com.app.milestone.repository;

import com.app.milestone.domain.QTalentDTO;
import com.app.milestone.domain.Search;
import com.app.milestone.domain.TalentDTO;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.app.milestone.entity.QDonation.donation;
import static com.app.milestone.entity.QPeople.people;
import static com.app.milestone.entity.QTalent.talent;

@Repository
@RequiredArgsConstructor
public class TalentCustomRepositoryImpl implements TalentCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    //동적쿼리
    // 재능기부 목록 (가능일자 ASC)
    @Override
    public List<TalentDTO> findAllByTalentAbleDate(Pageable pageable, Search search) {

        //pageable쓰면 pageable.of(파라미터 2개를 받을수있다) 첫번째가 현재페이지(page), 두번째가 페이지 사이즈(amount)
        return jpaQueryFactory.select(new QTalentDTO (
                talent.people.userId,
                talent.talentTitle,
                talent.talentContent,
                talent.talentAbleDate,
                talent.talentCategory,
                talent.talentPlace
        ))
                .where(
                        talentTitleContaining(search.getTalentTitle())
//                        talentCategoryContaining(search.getTalentCategory()),
//                        talentPlaceContaining(search.getTalentPlace())
                )
                .from(talent)
                .orderBy(talent.talentAbleDate.desc())
                .offset(pageable.getOffset()) //offset은 어디서부터 == page
                .limit(pageable.getPageSize()) //limit몇개를 들고오겠다 == amount
                .fetch();
    }

    //BooleanExpression은 null 일때 무시될 수 있고, and또는 or절을 통해서 조합을 할 수 있다.
    private BooleanExpression talentTitleContaining(String talentTitle) { //BooleanExpression null을 받으면 이메소드 사라진다.
        return StringUtils.hasText(talentTitle) ? talent.talentTitle.contains(talentTitle) : null;
    }

//    private BooleanExpression talentCategoryContaining(String talentCategory) {
//        return talentCategory.size()>0 ? talent.talentCategory.in(talentCategory) : null;
//    }

//    private BooleanExpression talentPlaceContaining(List<String> talentPlace) {
//        return talentPlace.size()>0 ? talent.talentPlace.in(talentPlace) : null;
//    }


//          =========페이징 처리===========
//          .offset(0) == 0부터 시작한다.
//          .limit(10) == 10개 가져온다.
//          Page<User> userPaging = new UserImpl<> (users, PageRequest.of(page0,amount10),total);
//          userPaging.getTotalPages(); == 페이지 전부
//          userPaging.isFirst(); == 첫번째 페이지
//          userPaging.getContent(); == 1페이지에 있는 user List
//          =============================

    @Override
    public List<TalentDTO> talentDetail(Long userId) {
        return jpaQueryFactory.select(new QTalentDTO (
                talent.people.userId,
                talent.talentTitle,
                talent.talentContent,
                talent.talentAbleDate,
                talent.talentCategory,
                talent.talentPlace
        )).from(talent, donation, people)
                .where(talent.donationId.eq(donation.donationId))
                .where(donation.people.userId.eq(people.userId))
                .where(people.userId.eq(userId))
                .fetch();
    }

    /*@Override
    public List<TalentDTO> talentLikeExercise(TalentDTO talentDTO) {
       List<Talent> exercise =  jpaQueryFactory
                .selectFrom(talent)
                .where(talent.talentCategory.like("운동")).fetch();
       return exercise;
    };*/



}
