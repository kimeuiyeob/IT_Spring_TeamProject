package com.app.milestone.repository;

import com.app.milestone.domain.NoticeDTO;
import com.app.milestone.domain.QNoticeDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.milestone.entity.QNotice.notice;


@Repository
@RequiredArgsConstructor
public class NoticeCustomRepositoryImpl implements NoticeCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    //공지 전체목록 : 제목, 작성일을 페이징처리로 가져오기
    @Override
    public List<NoticeDTO> findAllByCreateDate(Pageable pageable) {
//        return jpaQueryFactory.select(new QNoticeDTO(
//                notice.noticeTitle,
//                notice.noticeContent
//        ))
//                .from(notice)
//                .orderBy(notice.createdDate.desc())
//                .offset(0)
//                .limit(7)
//                .fetch();

        return jpaQueryFactory.select(new QNoticeDTO(
                notice.noticeTitle,
                notice.noticeContent
        ))
                .from(notice)
                .orderBy(notice.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
