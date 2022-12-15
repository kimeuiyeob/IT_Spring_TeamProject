package com.app.milestone.repository;

import com.app.milestone.domain.NoticeDTO;
import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.domain.Search;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoticeCustomRepository {
    public List<NoticeDTO> findCreatedDate(Pageable pageable);
    public List<NoticeDTO> findCreatedDateAsc(Pageable pageable);

    public NoticeDTO findByNoticeId(Long noticeId);
    public Long countByCreatedDate (Pageable pageable, Search search);
    public List<NoticeDTO> findBySearch(Pageable pageable, Search search);

    //Notice공지사항 글 순서대로 조회
    public List<NoticeDTO> findBySearchAsc(Pageable pageable, Search search);
    //Notice공지사항 공지사항 상세페이지 왼쪽 7개 제목 순서대로 가져오는 메소드
    public List<NoticeDTO> selectNoticeAll();

}
