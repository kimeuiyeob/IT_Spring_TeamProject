package com.app.milestone.repository;

import com.app.milestone.domain.MoneyDTO;
import com.app.milestone.entity.Money;
import com.querydsl.core.Tuple;

import java.util.List;

public interface MoneyCustomRepository {
    public List<Tuple> moneyRankingByOne(Long userId);
    public List<MoneyDTO> findByCreateDateByUserId(Long userId);
    public List<Tuple> sortByMoneyCash();
}
