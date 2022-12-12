package com.app.milestone.controller.school;

import com.app.milestone.domain.MoneyDTO;
import com.app.milestone.domain.PeopleDTO;
import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.domain.Search;
import com.app.milestone.entity.People;
import com.app.milestone.entity.User;
import com.app.milestone.service.PeopleService;
import com.app.milestone.service.SchoolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/school/*")
@Slf4j
public class SchoolController {
    private final SchoolService schoolService;
    private final PeopleService peopleService;

    //    보육원 목록
    @GetMapping("/list")
    public void list(HttpServletRequest request, Search search, Model model) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
//        Long userId = null;
//        if (user != null) {
//            userId = user.getUserId();
//        }
        model.addAttribute("userId", userId);
        model.addAttribute("search", search);
    }

    //    보육원 상세
    @GetMapping("/read")
    public void read(HttpServletRequest request, Long userId, Model model) {
        HttpSession session = request.getSession();
        Long sessionId = (Long) session.getAttribute("userId");
        PeopleDTO peopleDTO = null;
        SchoolDTO schoolDTO = null;
        if (sessionId != null) {
            peopleDTO = peopleService.onesInfo(sessionId);
            if (peopleDTO == null) {
                schoolDTO = schoolService.schoolInfo(sessionId);
            }
        }
        model.addAttribute("peopleDTO", peopleDTO);
        model.addAttribute("schoolDTO", schoolDTO);
        model.addAttribute("userId", userId);
    }

    //    ======================결제==========================
    //    결제페이지 이동
    @GetMapping("/donation")
    public void donation(Long userId, Model model) {
        model.addAttribute("schoolDTO", schoolService.schoolInfo(userId));
    }

    //    결제진행
    @PostMapping("/payment")
    public RedirectView payment(@RequestBody MoneyDTO moneyDTO) {
//        replyService.register(replyDTO);
        return new RedirectView("/school/read");
    }

    //    마이페이지 등록
    @GetMapping("/school")
    public RedirectView school(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        model.addAttribute("schoolDTO", schoolService.schoolInfo(763L));
        return new RedirectView("/mypage/schoolinfo");
    }
}