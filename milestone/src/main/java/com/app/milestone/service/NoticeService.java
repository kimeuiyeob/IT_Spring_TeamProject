package com.app.milestone.service;

import com.app.milestone.domain.NoticeDTO;
import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.domain.Search;
import com.app.milestone.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

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

    //    공지사항 삭제
    public void deleteByNoticeId(Long noticeId){
        noticeRepository.deleteById(noticeId);
    }

    //    총 공지사항 목록 수
    public Long noticeListCount(Pageable pageable, Search search) {
        return noticeRepository.countByCreatedDate(pageable, search);
    }

}

