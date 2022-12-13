package com.app.milestone.service;

import com.app.milestone.domain.FileDTO;
import com.app.milestone.entity.File;
import com.app.milestone.entity.User;
import com.app.milestone.repository.FileRepository;
import com.app.milestone.repository.UserRepository;
import com.app.milestone.type.FileType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {
    private final FileRepository fileRepository;
    private final UserRepository userRepository;

    //    추가
    @Transactional
    public void register(Long userId, FileDTO fileDTO) {
        log.info("============="+userId);
        log.info("============="+userRepository.findById(userId).get());
        log.info("============="+fileDTO);
        User user = userRepository.findById(userId).get();
        fileRepository.deleteByUserId(userId);
        fileRepository.save(fileDTO.toEntity()).changeUser(user);
    }

    //    삭제
    public void remove(Long fileId) {
        fileRepository.deleteById(fileId);
    }

//    프로필 조회
    public FileDTO showProfile(Long userId) {
        return fileRepository.findProfileByUserId(userId);
    }

    //    전체조회
    public List<FileDTO> showAll(Long userId) {
        return fileRepository.findByUserId(userId);
    }

}
