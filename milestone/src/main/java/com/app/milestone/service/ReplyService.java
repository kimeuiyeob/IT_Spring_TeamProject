package com.app.milestone.service;

import com.app.milestone.domain.ReplyDTO;
import com.app.milestone.entity.People;
import com.app.milestone.entity.Reply;
import com.app.milestone.entity.School;
import com.app.milestone.repository.PeopleRepository;
import com.app.milestone.repository.ReplyRepository;
import com.app.milestone.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final PeopleRepository peopleRepository;
    private final SchoolRepository schoolRepository;

    //    추가
    public void register(ReplyDTO replyDTO) {
        People people = peopleRepository.findById(replyDTO.getPeopleUserId()).get();
        School school = schoolRepository.findById(replyDTO.getSchoolUserId()).get();
        Reply reply = replyRepository.save(replyDTO.toEntity());
        reply.changeSchool(school);
        reply.changePeople(people);
    }

    //    조회
    public List<ReplyDTO> showAll(Long userId) {
        List<ReplyDTO> arReplyDTO = replyRepository.findBySchoolId(userId);
        for (ReplyDTO replyDTO : arReplyDTO) {
            String userName = peopleRepository.findById(replyDTO.getPeopleUserId()).get().getUserName();
            replyDTO.setUserName(userName);
        }
        return arReplyDTO;
    }

    //    수정
    public void modify(ReplyDTO replyDTO) {
        replyRepository.findById(replyDTO.getReplyId()).get().update(replyDTO.getReplyContent());
    }

    //    삭제
    public void remove(Long replyId) {
        replyRepository.deleteById(replyId);
    }
}
