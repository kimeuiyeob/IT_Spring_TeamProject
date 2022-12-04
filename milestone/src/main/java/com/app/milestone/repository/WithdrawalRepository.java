package com.app.milestone.repository;

import com.app.milestone.entity.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface WithdrawalRepository extends JpaRepository<Withdrawal, Long>, WithdrawalCustomRepository {
}
