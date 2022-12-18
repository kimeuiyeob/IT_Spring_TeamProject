package com.app.milestone.service;

import com.app.milestone.domain.NoticeDTO;
import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.domain.Search;
import com.app.milestone.entity.Notice;
import com.app.milestone.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoticeService {
    private final NoticeRepository noticeRepository;

    //  공지사항 전체목록 및 검색목록 가져오기
    public Page<NoticeDTO> noticeListBySearch(Integer page, Search search) {
        if (page == null) page = 0;
        Pageable pageable = PageRequest.of(page, 7);
        if (search.getNoticeTitle() == null) {
            search.setSchoolName(null);
        }
        List<NoticeDTO> list = noticeRepository.findBySearch(pageable, search);
        int start = list.size() >= (int) pageable.getOffset() ? (int) pageable.getOffset() : (int) pageable.getOffset() - 10;
        int end = Math.min((start + pageable.getPageSize()), list.size());

        Page<NoticeDTO> notices = new PageImpl<>(list.subList(start, end), pageable, Integer.valueOf("" + noticeRepository.countByCreatedDate(pageable, search)));

        return notices;
    }

    //  Notice상세페이지 왼쪽 최근세션7개 제목 가져오는 메소드
    public List<NoticeDTO> selectNoticeAll() {
        List<NoticeDTO> list = noticeRepository.selectNoticeAll();
        return list;
    }

    //  Notice공지사항 글 가져오기
    public Page<NoticeDTO> noticeListBySearchAsc(Integer page, Search search) {
        if (page == null) page = 0;
        Pageable pageable = PageRequest.of(page, 7);
        if (search.getNoticeTitle() == null) {
            search.setSchoolName(null);
        }
        List<NoticeDTO> list = noticeRepository.findBySearchAsc(pageable, search);
        int start = list.size() >= (int) pageable.getOffset() ? (int) pageable.getOffset() : (int) pageable.getOffset() - 10;
        int end = Math.min((start + pageable.getPageSize()), list.size());

        Page<NoticeDTO> notices = new PageImpl<>(list.subList(start, end), pageable, Integer.valueOf("" + noticeRepository.countByCreatedDate(pageable, search)));

        return notices;
    }

    //    공지사항 등록
    public void registerNotice(NoticeDTO noticeDTO){
        noticeRepository.save(noticeDTO.toEntity());
    }

    //    공지사항 수정
    @Transactional
    public NoticeDTO modifyNotice(NoticeDTO noticeDTO){
        Long noticeId = noticeDTO.getNoticeId();
        Notice notice = noticeRepository.findById(noticeId).get();
        notice.update(noticeDTO.getNoticeTitle(), noticeDTO.getNoticeContent());

        return noticeRepository.findByNoticeId(noticeId);
    }

    //    공지사항 삭제
    public void deleteByNoticeId(Long noticeId){
        noticeRepository.deleteById(noticeId);
    }

    //    공지사항 검색 목록 수
    public Long noticeListCount(Pageable pageable, Search search) {
        return noticeRepository.countByCreatedDate(pageable, search);
    }

    //    공지사항 정보하나
    public NoticeDTO noticeInfo(Long noticeId){
        return noticeRepository.findByNoticeId(noticeId);
    }
}

