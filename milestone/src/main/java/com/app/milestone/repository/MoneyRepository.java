package com.app.milestone.repository;


import com.app.milestone.entity.Money;
import com.querydsl.core.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MoneyRepository extends JpaRepository<Money, Long>, MoneyCustomRepository {
    public List<Money> findByOrderByCreatedDateDesc();

//    public List<Tuple> findByMoneyCashRankingByOne();
}
