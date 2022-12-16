package com.app.milestone.repository;

import com.app.milestone.domain.*;
import com.app.milestone.entity.Notice;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.app.milestone.entity.QNotice.notice;
import static com.app.milestone.entity.QSchool.school;


@Repository
@RequiredArgsConstructor
public class NoticeCustomRepositoryImpl implements NoticeCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public NoticeDTO findByNoticeId(Long noticeId){
        return jpaQueryFactory.select(new QNoticeDTO(
                notice.noticeId,
                notice.noticeTitle,
                notice.noticeContent,
                notice.createdDate
        ))
                .from(notice)
                .where(notice.noticeId.eq(noticeId))
                .fetchOne();
    };

    @Override
    public List<NoticeDTO> findCreatedDate(Pageable pageable) {
        return jpaQueryFactory.select(new QNoticeDTO(
                notice.noticeId,
                notice.noticeTitle,
                notice.noticeContent,
                notice.createdDate
        ))
                .from(notice)
                .orderBy(notice.createdDate.desc())
                .fetch();
    }

    @Override
    public List<NoticeDTO> findCreatedDateAsc(Pageable pageable) {
        return jpaQueryFactory.select(new QNoticeDTO(
                notice.noticeId,
                notice.noticeTitle,
                notice.noticeContent,
                notice.createdDate
        ))
                .from(notice)
                .orderBy(notice.createdDate.asc())
                .fetch();
    }

    @Override
    public Long countByCreatedDate(Pageable pageable, Search search) {
        return jpaQueryFactory.select(notice.count())
                .from(notice)
                .where(
                        titleContaining(search.getNoticeTitle())
                )
                .orderBy(notice.createdDate.desc())
                .fetchOne();
    }

    /*Notice 공지사항 찾아오기*/
    @Override
    public List<NoticeDTO> findBySearchAsc(Pageable pageable, Search search) {
        return jpaQueryFactory.select(new QNoticeDTO(
                notice.noticeId,
                notice.noticeTitle,
                notice.noticeContent,
                notice.createdDate
        ))
                .from(notice)
                .where(
                        titleContaining(search.getNoticeTitle())
                )
                .orderBy(notice.createdDate.desc())
                .fetch();
    }


    //Notice공지사항 공지사항 상세페이지 왼쪽 7개 제목 순서대로 가져오는 메소드
    @Override
    public List<NoticeDTO> selectNoticeAll() {
        return jpaQueryFactory.select(new QNoticeDTO(
                notice.noticeId,
                notice.noticeTitle,
                notice.noticeContent,
                notice.createdDate
        ))
                .from(notice)
                .orderBy(notice.createdDate.desc())
                .limit(7)
                .fetch();
    }

    @Override
    public List<NoticeDTO> findBySearch(Pageable pageable, Search search) {
        return jpaQueryFactory.select(new QNoticeDTO(
                notice.noticeId,
                notice.noticeTitle,
                notice.noticeContent,
                notice.createdDate
        ))
                .from(notice)
                .where(
                        titleContaining(search.getNoticeTitle())
                )
                .orderBy(notice.createdDate.desc())
                .fetch();
    }

    //    제목 검색
    private BooleanExpression titleContaining(String noticeTitle) {
        return StringUtils.hasText(noticeTitle) ? notice.noticeTitle.contains(noticeTitle) : null;
    }
}
