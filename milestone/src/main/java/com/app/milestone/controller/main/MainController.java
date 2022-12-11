package com.app.milestone.controller.main;

import com.app.milestone.SessionConst;
import com.app.milestone.domain.PeopleDTO;
import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.domain.SessionManager;
import com.app.milestone.entity.User;
import com.app.milestone.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/main/*")
@Slf4j
public class MainController {
    private final SchoolService schoolService;
    private final PeopleService peopleService;
    private final MoneyService moneyService;
    private final TalentService talentService;
    private final ServiceService serviceService;
    private final SessionManager sessionManager;

    @PostMapping("/main/people")
    public String createPeople(@Valid PeopleDTO peopleDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "join/joinUser";
        }
        peopleService.createPeople(peopleDTO);

        return "redirect:/main/main";
    }

    @PostMapping("/main/school")
    public String createSchool(@Valid SchoolDTO schoolDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "join/joinSchool";
        }
        schoolService.createSchool(schoolDTO);

        return "redirect:/main/main";
    }

    @PostMapping("/main/OAuth")
    public String createOAuth(@Valid PeopleDTO peopleDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "join/joinOAuth";
        }
        peopleService.createPeople(peopleDTO);

        return "redirect:/main/main";
    }

    @GetMapping("main")
    public void main(Model model) {
//        도움이 필요한 보육원
        model.addAttribute("moneys", moneyService.donationMoneyRanking());
        model.addAttribute("services", serviceService.donationVisitRanking());
        model.addAttribute("talents", peopleService.donationTalentRanking());
        model.addAttribute("schools", schoolService.needHelpList());
    }

    // 서블릿 HTTP 세션 사용
    @PostMapping("/login")
    public String mainLogin(HttpServletRequest request, Model model) {
        log.info("들어옴");
        // getSession(true) 를 사용하면 처음 들어온 사용자도 세션이 만들어지기 때문에 false로 받음
        HttpSession session = request.getSession(false);
        if (session == null) {
            log.info("null 들어옴");
            return "main";
        }

        // Member 로 타입 캐스팅
        User loginMember = (User) session.getAttribute(SessionConst.LOGIN_MEMBER);

        // 세션에 회원 데이터가 없으면 home
        if (loginMember == null) {
            log.info("로그인 실패");
            return "main";

        }
        log.info("유저 : " + loginMember.toString());
        // 세션이 유지되면 로그인으로 이동
        model.addAttribute("loginDTO", loginMember);
        log.info("로그인 성공");
        return "main";
    }
}