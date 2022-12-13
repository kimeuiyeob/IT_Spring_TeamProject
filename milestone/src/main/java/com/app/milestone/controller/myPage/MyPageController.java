package com.app.milestone.controller.myPage;

import com.app.milestone.domain.FileDTO;
import com.app.milestone.domain.PeopleDTO;
import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.domain.Search;
import com.app.milestone.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/*")
@Slf4j
public class MyPageController {

    private final PeopleService peopleService;
    private final CertificationService certificationService;
    private final UserService userService;


    //    일반회원 정보 보기
    @GetMapping("/myinfo")
    public String myinfo(HttpSession session, Model model) throws Exception {
        Long userId = (Long) session.getAttribute("userId");
        model.addAttribute("fileDTO", fileService.showProfile(userId));
        model.addAttribute("peopleDTO", peopleService.onesInfo(userId));
        return "/myPage/myPage_myInfo";
    }
    //    프로필 등록
    @PostMapping("/profile")
    public void profile(HttpSession session, @RequestBody FileDTO fileDTO) {
        Long userId = (Long) session.getAttribute("userId");
        log.info("========================" + fileDTO);
        fileService.register(userId, fileDTO);
    }

    //    수정
    @PostMapping("/update")
    public RedirectView updatePeople(HttpSession session, PeopleDTO peopleDTO) {
        Long userId = (Long) session.getAttribute("userId");
        peopleService.updatePeople(userId, peopleDTO);
        return new RedirectView("/mypage/myinfo");
    }

    @PostMapping(value = {"/checkEmail"})
    public Long checkEmail(@RequestBody String userEmail) {
        return userService.checkEmail(userEmail);
    }

    //    마이페이지 휴대폰 인증
    @GetMapping("/phoneCheck")
    @ResponseBody
    public String sendSMS(@RequestParam("phone") String userPhoneNumber) { // 휴대폰 문자보내기
        int randomNumber = (int) ((Math.random() * (9999 - 1000 + 1)) + 1000);//난수 생성

        certificationService.certifiedPhoneNumber(userPhoneNumber, randomNumber);

        return Integer.toString(randomNumber);
    }

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
    private final FileService fileService;

    @GetMapping("/schoolinfo")
    public String schoolInfo(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        model.addAttribute("fileDTO", fileService.showProfile(1L));
        model.addAttribute("schoolDTO", schoolService.schoolInfo(1L));
        return "/myPage/myPage_schoolInfo";
    }

    @PostMapping("/register")
    public RedirectView register(HttpServletRequest request, SchoolDTO schoolDTO) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        schoolService.registerSchool(1L, schoolDTO);
        return new RedirectView("/mypage/schoolinfo");
    }
//    ==========================================

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
    public void talent(Search search) {
        ;
    }
}
