package com.app.milestone.service;

import com.app.milestone.domain.FileDTO;
import com.app.milestone.entity.User;
import com.app.milestone.repository.FileRepository;
import com.app.milestone.repository.UserRepository;
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

    //   프로필 추가
    @Transactional
    public void register(Long userId, FileDTO fileDTO) {
        log.info("=============" + userId);
        log.info("=============" + userRepository.findById(userId).get());
        log.info("=============" + fileDTO);
        fileRepository.deleteByUserId(userId);
        User user = userRepository.findById(userId).get();
        fileRepository.save(fileDTO.toEntity()).changeUser(user);
    }

    //    보육원 이미지 추가
    @Transactional
    public void register(Long userId, List<FileDTO> fileDTO) {
        User user = userRepository.findById(userId).get();
        for (FileDTO file : fileDTO) {
            fileRepository.save(file.toEntity()).changeUser(user);
        }
    }

    //    보육원 이미지 삭제
    @Transactional
    public void removeSchoolImg(Long userId) {
        fileRepository.deleteSchoolImgByUserId(userId);
//        User user = userRepository.findById(userId).get();
//        for (FileDTO file : fileDTO) {
//            fileRepository.save(file.toEntity()).changeUser(user);
//        }
    }

    //    삭제
    public void remove(Long userId) {
        fileRepository.deleteById(userId);
    }

    //    프로필 조회
    public FileDTO showProfile(Long userId) {
        FileDTO fileDTO = fileRepository.findProfileByUserId(userId);
        log.info("==================================userId========================" + userId);
        log.info("==================================fileDTO========================" + fileDTO);
        log.info("==========================================================");
        return fileDTO;
    }

    //    전체조회
    public List<FileDTO> showAll(Long userId) {
        return fileRepository.findByUserId(userId);
    }

}
