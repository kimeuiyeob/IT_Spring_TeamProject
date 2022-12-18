package com.app.milestone.service;

import com.app.milestone.domain.FileDTO;
import com.app.milestone.domain.PasswordDTO;
import com.app.milestone.domain.PeopleDTO;
import com.app.milestone.entity.Like;
import com.app.milestone.entity.People;
import com.app.milestone.entity.School;
import com.app.milestone.repository.FileRepository;
import com.app.milestone.repository.LikeRepository;
import com.app.milestone.repository.PeopleRepository;
import com.app.milestone.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final SchoolRepository schoolRepository;
    private final LikeRepository likeRepository;
    private final FileRepository fileRepository;

    //    회원가입
    public void createPeople(PeopleDTO peopleDTO) {
        People people = peopleDTO.toEntity();
        peopleDTO.setUserEmail(people.getUserEmail());
        peopleDTO.setUserPassword(Base64.getEncoder().encodeToString(people.getUserPassword().getBytes()));
        peopleDTO.setUserName(people.getUserName());
        peopleDTO.setPeopleNickname(people.getPeopleNickname());
        peopleDTO.setUserPhoneNumber(people.getUserPhoneNumber());
        peopleRepository.save(peopleDTO.toEntity());
    }

    //  정보수정
    @Transactional
    public void updatePeople(Long userId, PeopleDTO peopleDTO) {
        peopleRepository.findById(userId).get().update(peopleDTO);
    }

    //  비밀번호 수정
    @Transactional
    public void updatePeoplePassword(Long userId, PasswordDTO passwordDTO) {
        String changePassword = Base64.getEncoder().encodeToString(passwordDTO.getChangePassword().getBytes());
        passwordDTO.setChangePassword(changePassword);
        log.info("피플 서비스 유저 비밀번호 : " +changePassword);
        log.info("피플 서비스 유저 아이디 : " + userId);
        peopleRepository.findById(userId).get().updatePassword(passwordDTO);
    }

    //    개인회원 한 명의 정보
    public PeopleDTO onesInfo(Long userId) {
        PeopleDTO peopleDTO = peopleRepository.findInfoById(userId);
        log.info("======================================userId====================" + userId);
        log.info("======================================peopleDTO====================" + peopleDTO);
        log.info("==========================================================");
        return peopleDTO;
    }

    //    ============================좋아요=========================
    //    내가 누른 좋아요
    public List<Long> likeSchoolList(Long sessionId) {
        List<Long> arUserId = new ArrayList<>();
        List<Like> likes = likeRepository.findByPeopleUserId(sessionId);
        for (Like like : likes) {
            arUserId.add(like.getSchool().getUserId());
        }
        return arUserId;
    }

    //    좋아요 누름
    public Long likeSchool(Long userId, Long sessionId) {
        School school = schoolRepository.findById(userId).get();
        People people = peopleRepository.findById(sessionId).get();
        Like like = new Like(school, people);
        return likeRepository.save(like).getLikeId();
    }

    //    좋아요 개수
    public Long likeCount(Long userId) {
        return likeRepository.countBySchoolUserId(userId);
    }





    /*==========================정서림===========================*/
    //      전체목록 및 검색목록
    public Page<PeopleDTO> peopleListSearch(Integer page, String keyword) {
        if (page == null) page = 0;
        Pageable pageable = PageRequest.of(page, 7);
        if (keyword == null) {
            keyword = null;
        }

        // 개인회원의 프로필사진을 불러와서 같이 넣어줍니다.
        List<PeopleDTO> list = peopleRepository.findPeopleSearch(pageable, keyword);
        for (PeopleDTO peopleDTO : list) {
            FileDTO arFileDTO = fileRepository.findProfileByUserId(peopleDTO.getUserId());
            peopleDTO.setUserProfile(arFileDTO);
        }
        Page<PeopleDTO> people = new PageImpl<>(list, pageable, Integer.valueOf("" + peopleRepository.countByCreatedDate(pageable, keyword)));
        return people;
    }

    /*==========================정서림===========================*/
    public Page<PeopleDTO> peopleListSearchAsc(Integer page, String keyword) {
        if (page == null) page = 0;
        Pageable pageable = PageRequest.of(page, 7);
        if (keyword == null) {
            keyword = null;
        }

        List<PeopleDTO> list = peopleRepository.findPeopleSearchAsc(pageable, keyword);
        for (PeopleDTO peopleDTO : list) {
            FileDTO arFileDTO = fileRepository.findProfileByUserId(peopleDTO.getUserId());
            peopleDTO.setUserProfile(arFileDTO);
        }
        Page<PeopleDTO> people = new PageImpl<>(list, pageable, Integer.valueOf("" + peopleRepository.countByCreatedDate(pageable, keyword)));
        return people;
    }

    //    회원 삭제
    public void deleteByUserId(Long userId) {
        peopleRepository.deleteById(userId);
    }


}
