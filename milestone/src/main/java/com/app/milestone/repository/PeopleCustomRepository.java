package com.app.milestone.repository;

import com.app.milestone.domain.Rank;
import com.app.milestone.entity.Money;
import com.querydsl.core.Tuple;

import java.util.List;

public interface PeopleCustomRepository {
    public List<Tuple> sortByMoneyCash();
    public List<Tuple> sortByVisitRank();
}
