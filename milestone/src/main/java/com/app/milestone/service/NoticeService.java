package com.app.milestone.service;

import com.app.milestone.domain.NoticeDTO;
import com.app.milestone.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

//    공지사항 목록
//    public List<NoticeDTO> noticeDTOList(Pageable pageable){
//        return noticeRepository.findAllByCreateDate(pageable);
//    }

//    총 공지 수


//    공지사항 등록
    public void registerNotice(NoticeDTO noticeDTO){
        noticeRepository.save(noticeDTO.toEntity());
    }

}

