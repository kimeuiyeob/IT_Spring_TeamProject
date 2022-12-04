package com.app.milestone.repository;

import com.app.milestone.domain.QReplyDTO;
import com.app.milestone.domain.ReplyDTO;
import com.app.milestone.entity.QReply;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.milestone.entity.QReply.*;

@Repository
@RequiredArgsConstructor
public class ReplyCustomRepositoryImpl implements ReplyCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ReplyDTO> findBySchoolId(Long userId) {
        return jpaQueryFactory.select(new QReplyDTO(
                reply.replyContent,
                reply.school.userId,
                reply.people.userId
        )).from(reply)
                .where(reply.school.userId.eq(userId))
                .fetch();
    }
}
