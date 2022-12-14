package com.app.milestone.service;

import com.app.milestone.domain.ReplyDTO;
import com.app.milestone.entity.People;
import com.app.milestone.entity.Reply;
import com.app.milestone.entity.School;
import com.app.milestone.entity.User;
import com.app.milestone.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;
    private final PeopleRepository peopleRepository;
    private final SchoolRepository schoolRepository;
    private final FileRepository fileRepository;

    //    추가
    @Transactional
    public void register(Long userId, ReplyDTO replyDTO) {
        User user = userRepository.findById(userId).get();
        School school = schoolRepository.findById(replyDTO.getSchoolUserId()).get();
        Reply reply = replyRepository.save(replyDTO.toEntity());
        reply.changeSchool(school);
        reply.changePeople(user);
    }

    //    조회
    public Page<ReplyDTO> showAll(Integer page, Long userId) {
        if (page == null) page = 0;
        Pageable pageable = PageRequest.of(0, (page + 1) * 10);
        List<ReplyDTO> list = replyRepository.findBySchoolId(pageable, userId);
        for (ReplyDTO replyDTO : list) {
            Optional<School> optionalSchool = schoolRepository.findById(replyDTO.getUserId());
            Optional<People> optionalPeople = peopleRepository.findById(replyDTO.getUserId());
            log.info("댓글 =====================" + schoolRepository.findById(replyDTO.getUserId()));
            log.info("댓글 =====================" + peopleRepository.findById(replyDTO.getUserId()).isPresent());
            if(optionalSchool.isPresent()){
                replyDTO.setSchoolName(optionalSchool.get().getSchoolName());
            }
            if(optionalPeople.isPresent()){
                replyDTO.setPeopleNickName(optionalPeople.get().getPeopleNickname());
            }

//            if (school != null) {
//                replyDTO.setSchoolName(school.getSchoolName());
//            }
//            if (people != null) {
//                replyDTO.setPeopleNickName(people.getPeopleNickname());
//            }

            replyDTO.setFile(fileRepository.findProfileByUserId(replyDTO.getUserId()));
        }
        Page<ReplyDTO> replys = new PageImpl<>(list, pageable, replyRepository.countBySchoolId(userId));
        return replys;
    }

//    //    댓글 개수
//    public Long replyCount(Long userId) {
//        return replyRepository.countBySchoolId(userId);
//    }

    //    수정
    @Transactional
    public void modify(ReplyDTO replyDTO) {
        replyRepository.findById(replyDTO.getReplyId()).get().update(replyDTO.getReplyContent());
    }

    //    삭제
    public void remove(Long replyId) {
        replyRepository.deleteById(replyId);
    }
}
