package com.app.milestone.repository;

import com.app.milestone.domain.NoticeDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoticeCustomRepository {
    public List<NoticeDTO> findAllByCreateDate (Pageable pageable);
}
