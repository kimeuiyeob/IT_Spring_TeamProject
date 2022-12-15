package com.app.milestone.repository;

import com.app.milestone.domain.FileDTO;

import java.util.List;

public interface FileCustomRepository {
    public List<FileDTO> findByUserId(Long userId);
    public FileDTO findProfileByUserId(Long userId);
}
