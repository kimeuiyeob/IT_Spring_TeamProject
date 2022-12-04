package com.app.milestone.repository;

import com.app.milestone.domain.QWithdrawalDTO;
import com.app.milestone.domain.WithdrawalDTO;
import com.app.milestone.entity.QWithdrawal;
import com.app.milestone.entity.Withdrawal;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.milestone.entity.QWithdrawal.withdrawal;

@Repository
@RequiredArgsConstructor
public class WithdrawalRepositoryImpl implements WithdrawalCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    //    탈퇴회원 목록
    @Override
    public List<Withdrawal> findByCreatedDate (Pageable pageable) {
        return jpaQueryFactory.selectFrom(withdrawal)
                .orderBy(withdrawal.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    };
//    public List<WithdrawalDTO> findByCreatedDate (Pageable pageable) {
//        return jpaQueryFactory.select(new QWithdrawalDTO(
//            withdrawal.withdrawalUserType,
//            withdrawal.withdrawalReason
//        )).from(withdrawal)
//                .orderBy(withdrawal.createdDate.desc())
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//    };

}
