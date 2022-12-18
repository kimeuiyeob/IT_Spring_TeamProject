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

    //  공지사항 하나
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

    //  공지사항 목록 : 최신순
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

    //  공지사항 목록 : 오래된순
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

    //  검색 총 페이지수
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

    //  검색 목록 : 작성일 최신순
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

    //  검색 목록 : 작성일 오래된순
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
                .orderBy(notice.createdDate.asc())
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

    //    제목검색 메소드
    private BooleanExpression titleContaining(String noticeTitle) {
        return StringUtils.hasText(noticeTitle) ? notice.noticeTitle.contains(noticeTitle) : null;
    }
}
