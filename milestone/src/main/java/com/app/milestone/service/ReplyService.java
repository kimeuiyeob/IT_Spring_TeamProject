package com.app.milestone.service;

import com.app.milestone.domain.ReplyDTO;
import com.app.milestone.entity.People;
import com.app.milestone.entity.Reply;
import com.app.milestone.entity.School;
import com.app.milestone.repository.PeopleRepository;
import com.app.milestone.repository.ReplyRepository;
import com.app.milestone.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final PeopleRepository peopleRepository;
    private final SchoolRepository schoolRepository;

    //    추가
    @Transactional
    public void register(Long userId, ReplyDTO replyDTO) {
        People people = peopleRepository.findById(userId).get();
        School school = schoolRepository.findById(replyDTO.getSchoolUserId()).get();
        Reply reply = replyRepository.save(replyDTO.toEntity());
        reply.changeSchool(school);
        reply.changePeople(people);
    }

    //    조회
    public Page<ReplyDTO> showAll(Integer page, Long userId) {
        if (page == null) page = 0;
        Pageable pageable = PageRequest.of(0, (page + 1) * 10);
        List<ReplyDTO> list = replyRepository.findBySchoolId(pageable, userId);
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
