package com.app.milestone.repository;

import com.app.milestone.domain.Search;
import com.app.milestone.entity.QUser;
import com.app.milestone.entity.School;
import com.app.milestone.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import static com.app.milestone.entity.QUser.user;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<User> findByCreatedDate(Pageable pageable) {
        return jpaQueryFactory.selectFrom(user)
                .orderBy(user.createdDate.desc())
//                .orderBy(user.createdDate.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
