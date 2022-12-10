package com.app.milestone.repository;

import com.app.milestone.domain.ReplyDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReplyCustomRepository {
    public List<ReplyDTO> findBySchoolId(Pageable pageable, Long userId);
    public Long countBySchoolId(Long userId);
}
