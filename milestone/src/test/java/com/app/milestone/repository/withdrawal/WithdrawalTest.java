package com.app.milestone.repository.withdrawal;

import com.app.milestone.domain.PeopleDTO;
import com.app.milestone.domain.WithdrawalDTO;
import com.app.milestone.entity.Withdrawal;
import com.app.milestone.repository.WithdrawalRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static com.app.milestone.entity.QNotice.notice;
import static com.app.milestone.entity.QSchool.school;
import static com.app.milestone.entity.QWithdrawal.withdrawal;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class WithdrawalTest {
    @Autowired
    private WithdrawalRepository withdrawalRepository;
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

//    회원탈퇴 저장
    @Test
    public void saveTest() {
//        WithdrawalDTO withdrawalDTO = new WithdrawalDTO();
//        withdrawalDTO.setWithdrawalReason("대체할 만한 서비스를 찾았어요");
//        withdrawalDTO.setWithdrawalUserType("보육원");                   //사용자 형태
//        withdrawalRepository.save(withdrawalDTO.toEntity());

        String[] reasons = {"이용하고 싶은 서비스가 없어요", "서비스 퀄리티가 낮아요", "비매너 회원을 만났어요", "잦은 오류가 발생해요", "대체할 만한 서비스를 찾았어요"};
        String[] types = {"보육원", "일반"};
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        WithdrawalDTO withdrawalDTO = new WithdrawalDTO();
            withdrawalDTO.setWithdrawalReason(reasons[i % 5]);
            withdrawalDTO.setWithdrawalUserType(types[i % 2]);

            withdrawalRepository.save(withdrawalDTO.toEntity());
        }



    }

//    회원탈퇴 검색어(사용자 형태, 탈퇴이유)로 찾기
    @Test
    public void findBySearchTest() {
        jpaQueryFactory.selectFrom(withdrawal)
                .where(withdrawal.withdrawalUserType.contains("보육"))      //사용자 형태
//                .where(withdrawal.withdrawalReason.contains("대체할"))    //탈퇴 사유
                .fetch().forEach(withdrawal->log.info("검색어로 찾아온 탈퇴 사유 : "+withdrawal.getWithdrawalReason()));
    }

//    회원탈퇴 필터(탈퇴날짜, 탈퇴이유)로 찾기
    @Test
    public void findTest() {
        jpaQueryFactory
                .selectFrom(withdrawal)
                .where(withdrawal.withdrawalReason.eq("대체할 만한 서비스를 찾았어요"))
                .orderBy(withdrawal.createdDate.desc())
//                .orderBy(withdrawal.createdDate.asc())
                .fetch().stream().map(Withdrawal::toString).forEach(log::info);

//        동적쿼리 <<<안써도 될듯하다
//        jpaQueryFactory.selectFrom(withdrawal)
//                .where(
//                        schoolNameContainingAndLocationContaining(search.getSchoolName(), search.getSchoolAddress())
//                        schoolNameContaining(search.getSchoolName()),
//                        schoolAddressContaining(search.getSchoolName())
//                        school.address.schoolAddress.in(search.getSchoolAddress())
//                )
//                .orderBy(withdrawal.createdDate.desc())
//                .fetch();
    }


}
