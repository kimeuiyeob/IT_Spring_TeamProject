package com.app.milestone.service;

import com.app.milestone.domain.FileDTO;
import com.app.milestone.entity.File;
import com.app.milestone.entity.User;
import com.app.milestone.repository.FileRepository;
import com.app.milestone.repository.UserRepository;
import com.app.milestone.type.FileType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;
    private final UserRepository userRepository;

    //    추가
    public void register(FileDTO fileDTO) {
        User user = userRepository.findById(fileDTO.getUserId()).get();
        File file = new File(fileDTO.getFileName(), fileDTO.getFilePath(), fileDTO.getFileUuid(), fileDTO.getFileSize(), fileDTO.getFileType(), user);
        fileRepository.save(file);
    }

    //    삭제
    public void remove(Long fileId) {
        fileRepository.deleteById(fileId);
    }

    //    전체조회
    public List<FileDTO> showAll(Long userId) {
        return fileRepository.findByUserId(userId);
    }

}
