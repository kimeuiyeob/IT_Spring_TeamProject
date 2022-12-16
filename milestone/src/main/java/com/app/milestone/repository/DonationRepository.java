package com.app.milestone.repository;

import com.app.milestone.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;

@Component
public interface DonationRepository extends JpaRepository<Donation, Long>, DonationCustomRepository {
    public int countByPeopleUserId(Long userId);
    public int countBySchoolUserId(Long userId);
    //회원 탈퇴시 도네이션 삭제
    public void deleteByPeopleUserId(Long userId);
    public void deleteBySchoolUserId(Long userId);

}
