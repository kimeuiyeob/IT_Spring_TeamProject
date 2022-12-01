package com.app.milestone.repository;

import com.app.milestone.domain.Search;
import com.app.milestone.entity.Talent;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.app.milestone.entity.QTalent.talent;

@Repository
@RequiredArgsConstructor
public class TalentCustomRepositoryImpl implements TalentCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    //동적쿼리
    // 재능기부 목록 (가능일자 ASC)
    @Override
    public List<Talent> findAllByTalentAbleDate(Pageable pageable, Search search) {
        //pageable쓰면 pageable.of(파라미터 2개를 받을수있다) 첫번째가 현재페이지(page), 두번째가 페이지 사이즈(amount)
        return jpaQueryFactory.selectFrom(talent)
                .where(
                        talentTitleContaining(search.getTalentTitle()),
                        talent.category.in(search.getTalentCategory()),
                        talent.place.in(search.getSchoolAddress())
                )
                .orderBy(talent.talentAbleDate.asc())
                .offset(pageable.getOffset()) //offset은 어디서부터 == page
                .limit(pageable.getPageSize()) //limit몇개를 들고오겠다 == amount
                .fetch();
    }

    private BooleanExpression talentTitleContaining(String talentTitle) { //BooleanExpression null을 받으면 이메소드 사라진다.
        return StringUtils.hasText(talentTitle) ? talent.talentTitle.contains(talentTitle) : null;
    }

//          =========페이징 처리===========
//          .offset(0) == 0부터 시작한다.
//          .limit(10) == 10개 가져온다.
//          Page<User> userPaging = new UserImpl<> (users, PageRequest.of(page0,amount10),total);
//          userPaging.getTotalPages(); == 페이지 전부
//          userPaging.isFirst(); == 첫번째 페이지
//          userPaging.getContent(); == 1페이지에 있는 user List
//          =============================

}
