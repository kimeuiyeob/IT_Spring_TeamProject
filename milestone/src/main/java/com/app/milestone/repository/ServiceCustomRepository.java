package com.app.milestone.repository;

import com.querydsl.core.Tuple;

import java.util.List;

public interface ServiceCustomRepository {
    public List<Tuple> sortByVisitRank();
}
