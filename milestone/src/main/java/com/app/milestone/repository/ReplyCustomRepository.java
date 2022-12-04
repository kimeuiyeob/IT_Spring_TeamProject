package com.app.milestone.repository;

import com.app.milestone.domain.ReplyDTO;

import java.util.List;

public interface ReplyCustomRepository {
    public List<ReplyDTO> findBySchoolId(Long userId);
}
