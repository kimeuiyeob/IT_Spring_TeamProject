package com.app.milestone.repository;

import com.app.milestone.domain.QWithdrawalDTO;
import com.app.milestone.domain.Search;
import com.app.milestone.domain.WithdrawalDTO;
import com.app.milestone.entity.QWithdrawal;
import com.app.milestone.entity.Withdrawal;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.app.milestone.entity.QPeople.people;
import static com.app.milestone.entity.QSchool.school;
import static com.app.milestone.entity.QWithdrawal.withdrawal;

@Repository
@RequiredArgsConstructor
public class WithdrawalRepositoryImpl implements WithdrawalCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    //    탈퇴회원 목록 내림차순(최신순)
    @Override
    public List<WithdrawalDTO> findByCreatedDate (Pageable pageable) {
        return jpaQueryFactory.select(new QWithdrawalDTO(
                withdrawal.withdrawalReason,
                withdrawal.withdrawalUserType,
                withdrawal.createdDate
        )).from(withdrawal)
                .orderBy(withdrawal.createdDate.desc())
                .fetch();
    };

    //    탈퇴회원 목록 오름차순(오래된 순)
    public List<WithdrawalDTO> findByCreatedDateAsc () {
        return jpaQueryFactory.select(new QWithdrawalDTO(
            withdrawal.withdrawalReason,
            withdrawal.withdrawalUserType,
            withdrawal.createdDate
            )).from(withdrawal)
                .orderBy(withdrawal.createdDate.asc())
                .fetch();
    };

    @Override
    public List<WithdrawalDTO> findWithdrawalSearch(Pageable pageable, Search search) {
        return jpaQueryFactory.select(new QWithdrawalDTO(
                withdrawal.withdrawalReason,
                withdrawal.withdrawalUserType,
                withdrawal.createdDate
        )).from(withdrawal)
                .orderBy(withdrawal.createdDate.desc())
                .fetch();
    }

    @Override
    public List<WithdrawalDTO> findWithdrawalSearchAsc(Pageable pageable, Search search) {
        return jpaQueryFactory.select(new QWithdrawalDTO(
                withdrawal.withdrawalReason,
                withdrawal.withdrawalUserType,
                withdrawal.createdDate
        )).from(withdrawal)
                .where(
                        reasonContaining(search.getWithdrawalReason())
                )
                .orderBy(withdrawal.createdDate.asc())
                .fetch();
    }

    @Override
    public Long countByCreatedDate(Pageable pageable, Search search) {
        return jpaQueryFactory.select(withdrawal.count())
                .from(withdrawal)
                .where(
                        reasonContaining(search.getWithdrawalReason())
                )
                .orderBy(withdrawal.createdDate.asc())
                .fetchOne();
    }

    //    이름검색
    private BooleanExpression reasonContaining(String reason) {
        return StringUtils.hasText(reason) ? withdrawal.withdrawalReason.contains(reason) : null;
    }
}
