package com.app.milestone.controller.myPage;

import com.app.milestone.domain.Search;
import com.app.milestone.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/*")
public class MyPageController {
    

    @GetMapping("/myinfo")
    public String myInfo() {
        return "/myPage/myPage_myInfo";
    }

    ;

    @GetMapping("/password")
    public String password() {
        return "/myPage/myPage_password";
    }

    ;

    @GetMapping("/schedule")
    public String schedule() {
        return "/myPage/myPage_schedule";
    }

    ;
    
    /*황지수*/
    private final SchoolService schoolService;
    @GetMapping("/schoolinfo")
    public String schoolInfo(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        model.addAttribute("schoolDTO", schoolService.schoolInfo(763L));
        return "/myPage/myPage_schoolInfo";
    }

    @GetMapping("/likelist")
    public String likeList() {
        return "/myPage/myPage_likeList";
    }

    ;

    @GetMapping("/withdrawal")
    public String withdrawal() {
        return "/myPage/myPage_withdrawal";
    }

    ;

    @GetMapping("/school-schedule")
    public String schoolSchedule() {
        return "/myPage/myPage_school_schedule";
    }

    ;

    /*=============================아래부터 의엽씨가 건든거입니다.==================================*/
    @GetMapping("/alarm")
    public void alarm() {
       ;
    }

    @GetMapping("/talent")
    public void talent(Search search) {;
    }
}
