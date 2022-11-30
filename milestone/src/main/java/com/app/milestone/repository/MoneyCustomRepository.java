package com.app.milestone.repository;

import com.querydsl.core.Tuple;

import java.util.List;

public interface MoneyCustomRepository {
    public List<Tuple> moneyRankingByOne(Long userId);
}
