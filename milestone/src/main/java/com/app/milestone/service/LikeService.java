package com.app.milestone.service;

import com.app.milestone.domain.FileDTO;
import com.app.milestone.domain.LikeDTO;
import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.domain.Search;
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
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikeService {
    public final SchoolRepository schoolRepository;
    public final PeopleRepository peopleRepository;
    public final LikeRepository likeRepository;
    public final FileRepository fileRepository;

    //    ============================좋아요=========================
    //    내가 누른 좋아요
    public List<Long> likeSchoolList(Long userId) {
        List<Long> arUserId = new ArrayList<>();
        List<Like> likes = likeRepository.findByPeopleUserId(userId);
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

    //    좋아요 취소
    @Transactional
    public void cancelLikeSchool(Long userId, Long sessionId) {
        likeRepository.deleteByPeopleIdAndSchoolId(sessionId,userId);
    }

    //    좋아요 개수
    public Long likeCount(Long userId) {
        return likeRepository.countBySchoolUserId(userId);
    }


    /*==========================정서림===========================*/
    //    좋아요 목록 : 로그인한 회원의 찜목록 전체를 불러오는 메소드입니다.
    public Page<LikeDTO> likedSchools(Integer page, Long sessionId) {
        if (page == null) page = 0;
        Pageable pageable = PageRequest.of(page, 5);
        List<LikeDTO> list = likeRepository.findSchoolLiked(pageable, sessionId);

        //  보육원에 등록된 사진들을 가져옵니다.
        for(LikeDTO likeDTO:list){
            List<FileDTO> arFileDTO = fileRepository.findByUserId(likeDTO.getSchoolId());
            likeDTO.setFiles(arFileDTO);
        }
        Page<LikeDTO> likes = new PageImpl<>(list, pageable, Integer.valueOf("" + likeRepository.countByCreatedDate(pageable, sessionId)));
        return likes;
    }


    /*==========================정서림===========================*/
    //    좋아요 검색 : 로그인한 회원이 보육원 이름과 지역을 검색하여 가져온 목록입니다.
     public Page<LikeDTO> likedSchoolsSearch(Integer page, Long sessionId, Search search) {
        if (page == null) page = 0;
        if (search.getSchoolAddress() == null) {
            search.setSchoolAddress(new ArrayList<>());
        }

        Pageable pageable = PageRequest.of(page, 5);
        List<LikeDTO> list = likeRepository.findSchoolLikedSearch(pageable, sessionId, search);

        for(LikeDTO likeDTO:list){
            List<FileDTO> arFileDTO = fileRepository.findByUserId(likeDTO.getSchoolId());
            likeDTO.setFiles(arFileDTO);
        }
        Page<LikeDTO> likes = new PageImpl<>(list, pageable, Integer.valueOf("" + likeRepository.countByCreatedDate2(pageable, sessionId, search)));
        return likes;
    }

    /*==========================정서림===========================*/
    //   마이페이지 좋아요 개수
    public Long likeCountMyPage(Long userId) {
        return likeRepository.countByPeopleUserId(userId);
    }

    /*==========================정서림===========================*/
    //  마이페이지 좋아요 취소
    public void deleteByLikeId(Long likeId){
        likeRepository.deleteById(likeId);
    }

}
