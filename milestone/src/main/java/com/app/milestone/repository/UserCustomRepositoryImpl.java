package com.app.milestone.repository;

import com.app.milestone.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.app.milestone.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private static Map<Long, User> store = new HashMap<>();   // static 사용

    @Override
    public List<User> findByCreatedDate(Pageable pageable) {
        return jpaQueryFactory.selectFrom(user)
                .orderBy(user.createdDate.desc())
//                .orderBy(user.createdDate.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public User findByUserId(Long userId) {
        return (User) jpaQueryFactory
                .select(user.userId)
                .from(user)
                .where(user.userId.eq(userId))
                .fetch();
    }

    @Override
    public Optional<User> findByUserEmail(String userEmail) {
        return findAll().stream()
                .filter(u -> u.getUserEmail().equals(userEmail))
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        return jpaQueryFactory
                .selectFrom(user)
                .fetch();
    }

    @Override
    public void clearStore() {
        store.clear();
    }
}
