package com.app.milestone.repository;

import com.app.milestone.domain.MoneyDTO;
import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.entity.Money;
import com.querydsl.core.Tuple;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MoneyCustomRepository {
    public List<Tuple> moneyRankingByOne(Long userId);
    public List<MoneyDTO> findByCreateDateByUserId(Long userId);
    public List<Tuple> sortByMoneyCash();

    public List<MoneyDTO> findMoneySearch (Pageable pageable, String keyword);
    public List<MoneyDTO> findMoneySearchAsc (Pageable pageable, String keyword);
    public List<MoneyDTO> findMoneySearchAmount (Pageable pageable, String keyword);
    public List<MoneyDTO> findMoneySearchAmountAsc (Pageable pageable, String keyword);
    public Long countByCreatedDate(Pageable pageable, String keyword);
}
