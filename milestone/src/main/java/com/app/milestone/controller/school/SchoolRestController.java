package com.app.milestone.controller.school;

import com.app.milestone.domain.*;
import com.app.milestone.entity.People;
import com.app.milestone.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Transactional
@RequestMapping("/schoolrest/*")
@Slf4j
public class SchoolRestController {
    private final SchoolService schoolService;
    private final PeopleService peopleService;
    private final ReplyService replyService;
    private final LikeService likeService;
    private final MoneyService moneyService;
    private final ServiceService serviceService;

    //    보육원 목록
    @GetMapping(value = {"/list/{page}", "/list/{page}/{schoolAddress}", "/list/{page}/{schoolAddress}/{schoolName}"})
    public Page<SchoolDTO> search(@PathVariable("page") Integer page, Search search) {
        Page<SchoolDTO> arSchoolDTO = schoolService.schoolList(page, search);
        return arSchoolDTO;
    }

    //    ==========================보육원 상세=======================
    //    보육원 정보
    @GetMapping(value = {"/info/{userId}"})
    public SchoolDTO info(@PathVariable("userId") Long userId) {
        return schoolService.schoolInfo(userId);
    }

    //    보육원 최근 기부
    @GetMapping(value = {"/recent/{userId}"})
    public List<MoneyDTO> recent(@PathVariable("userId") Long userId) {
        return moneyService.recentDonationList(userId);
    }

    //    보육원 기부 랭킹
    @GetMapping(value = {"/ranking/{userId}"})
    public List<MoneyDTO> ranking(@PathVariable("userId") Long userId) {
        return moneyService.moneyDonationRankingForOneSchool(userId);
    }

    //    ================댓글=================

    //    보육원 댓글 보기
    @GetMapping(value = {"/reply/{page}/{userId}"})
    public Page<ReplyDTO> reply(@PathVariable("page") Integer page, @PathVariable("userId") Long userId) {
        return replyService.showAll(page, userId);
    }

    //    보육원 댓글 삭제
    @GetMapping(value = {"/{replyId}"})
    public void remove(@PathVariable("replyId") Long replyId) {
        replyService.remove(replyId);
    }

    //    보육원 댓글 수정
    @PostMapping("/modify")
    public void modify(@RequestBody ReplyDTO replyDTO) {
        replyService.modify(replyDTO);
    }

    //    보육원 댓글 등록
    @PostMapping("/register")
    public void register(HttpServletRequest request, @RequestBody ReplyDTO replyDTO) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        replyService.register(userId, replyDTO);
    }

    //  ==============좋아요=============
    //    내가 누른 좋아요
    @Transactional
    @GetMapping(value = {"/likeSchool"})
    public List<Long> likeSchool(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        return likeService.likeSchoolList(userId);
    }

    //    좋아요 개수
    @GetMapping(value = {"/likeCount/{userId}"})
    public Long likeCount(@PathVariable("userId") Long userId) {
        return likeService.likeCount(userId);
    }

    //    좋아요 누름
    @GetMapping(value = {"/like/{userId}"})
    public void like(HttpServletRequest request, @PathVariable("userId") Long userId) {
        HttpSession session = request.getSession();
        Long sessionId = (Long) session.getAttribute("userId");
        likeService.likeSchool(userId, sessionId);
    }

    //    좋아요 취소
    @GetMapping(value = {"/cancel/{userId}"})
    public void cancel(HttpServletRequest request, @PathVariable("userId") Long userId) {
        HttpSession session = request.getSession();
        Long sessionId = (Long) session.getAttribute("userId");
        likeService.cancelLikeSchool(userId, sessionId);
    }

    //    ======================기부==========================
    //    결제진행
    @PostMapping("/payment")
    public void payment(HttpServletRequest request, @RequestBody MoneyDTO moneyDTO) {
        HttpSession session = request.getSession();
        Long sessionId = (Long) session.getAttribute("userId");
        moneyService.payment(sessionId, moneyDTO);
    }

    //    방문기부
    @PostMapping("/visit")
    public boolean visit(HttpServletRequest request, @RequestBody ServiceDTO serviceDTO) {
        HttpSession session = request.getSession();
        Long sessionId = (Long) session.getAttribute("userId");
        return serviceService.donationReservation(sessionId, serviceDTO);
    }

}
