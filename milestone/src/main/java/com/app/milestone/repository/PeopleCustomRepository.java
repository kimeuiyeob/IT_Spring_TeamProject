package com.app.milestone.repository;

import com.querydsl.core.Tuple;

import java.util.List;

public interface PeopleCustomRepository {
    public List<Tuple> sortByMoneyCash();
    public List<Tuple> sortByVisitRank();
    public List<Tuple> sortBytalentRank();
}
