package com.app.milestone.repository;

import com.app.milestone.domain.FileDTO;
import com.app.milestone.entity.File;

import java.util.List;

public interface FileCustomRepository {
    public List<FileDTO> findByUserId(Long userId);
}
