package com.app.milestone.repository;


import com.app.milestone.entity.Money;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface MoneyRepository extends JpaRepository<Money, Long> {
}
