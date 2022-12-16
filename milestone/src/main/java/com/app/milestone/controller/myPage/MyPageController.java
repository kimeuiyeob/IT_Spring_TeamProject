package com.app.milestone.controller.myPage;

import com.app.milestone.domain.*;
import com.app.milestone.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/*")
@Slf4j
public class MyPageController {

    private final PeopleService peopleService;
    private final CertificationService certificationService;
    private final UserService userService;
    private final WithdrawalService withdrawalService;
    private final ServiceService serviceService;


    //    일반회원 정보 보기
    @GetMapping("/myinfo")
    public String myinfo(HttpSession session, Model model) throws Exception {
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            model.addAttribute("fileDTO", fileService.showProfile(userId));
        }
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

    //    일반회원 개인정보 수정
    @PostMapping("/update")
    public RedirectView updatePeople(HttpSession session, PeopleDTO peopleDTO) {
        Long userId = (Long) session.getAttribute("userId");
        peopleService.updatePeople(userId, peopleDTO);
        return new RedirectView("/mypage/myinfo");
    }

    //   비밀번호 변경
    @PostMapping("/peoplePasswordUpdate")
    public RedirectView updatePeoplePassword(HttpSession session, PasswordDTO passwordDTO) {
        Long userId = (Long) session.getAttribute("userId");
        log.info("마페컨 유저 비밀번호 : " + passwordDTO);
        log.info("마페컨 유저 아이디 : " + userId);
        peopleService.updatePeoplePassword(userId, passwordDTO);
        return new RedirectView("/main/main");
    }

    //   보육원 회원 비밀번호 변경
    @PostMapping("/schoolPasswordUpdate")
    public RedirectView updateSchoolPassword(HttpSession session, PasswordDTO passwordDTO) {
        Long userId = (Long) session.getAttribute("userId");
        log.info("마페컨 유저 비밀번호 : " + passwordDTO);
        log.info("마페컨 유저 아이디 : " + userId);
        schoolService.updateSchoolPassword(userId, passwordDTO);
        return new RedirectView("/main/main");
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

    //    개인 비밀번호 변경 화면
    @GetMapping("/peoplePassword")
    public String checkPasswordView() {
        return "mypage/myPage_password";
    }

    //    보육원 비밀번호 변경 화면
    @GetMapping("/schoolPassword")
    public String schoolCheckPasswordView() {
        return "mypage/myPage_password_school";
    }

    //    개인 일정 관리
    @GetMapping("/peopleSchedule")
    public String peopleSchedule(HttpSession session, Model model) throws Exception {
        Long userId = (Long) session.getAttribute("userId");
        model.addAttribute("serviceDTO", serviceService.findPeopleVisitDate(userId));
        return "/myPage/myPage_schedule";
    }

    //    보육원 일정 관리
    @GetMapping("/schoolSchedule")
    public String schoolSchedule(HttpSession session, Model model) throws Exception {
        Long userId = (Long) session.getAttribute("userId");
        model.addAttribute("serviceDTO1", serviceService.findSchoolVisitDate1(userId));
        return "/myPage/myPage_school_schedule";
    }

    /*황지수*/
    private final SchoolService schoolService;
    private final FileService fileService;

    @GetMapping("/schoolinfo")
    public String schoolInfo(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            model.addAttribute("fileDTO", fileService.showProfile(userId));
        }
        model.addAttribute("schoolDTO", schoolService.schoolInfo(userId));
        return "/myPage/myPage_schoolInfo";
    }

    //    application/json charset=utf-8
    //    보육원 등록(수정)
    @PostMapping("/register")
    public RedirectView register(HttpServletRequest request, SchoolDTO schoolDTO) {
        log.info("=================" + schoolDTO);
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        if (schoolDTO.getFiles() != null) {
            fileService.removeSchoolImg(userId);
            fileService.register(userId, schoolDTO.getFiles());
        }
        schoolService.registerSchool(userId, schoolDTO);
        return new RedirectView("/mypage/schoolinfo");
    }
//    ==========================================

    @GetMapping("/likelist")
    public String likeList() {
        return "/myPage/myPage_likeList";
    }

    ;

    @GetMapping("/withdrawalPeople")
    public String withdrawal() {
        return "/myPage/myPage_withdrawal";
    }

    @GetMapping("/withdrawalSchool")
    public String withdrawalSchool() {
        return "/myPage/myPage_withdrawalSchool";
    }

    ;;

    @GetMapping("/schoolAlarm")
    public String schoolAlarm() {
        return "/myPage/myPage_alarm_school";
    }

    /*=============================아래부터 의엽씨가 건든거입니다.==================================*/
    @GetMapping("/peopleAlarm")
    public String alarm() {
        return "/myPage/myPage_alarm";
    }


    @GetMapping("/talent")
    public void talent(Search search) {
        ;
    }

    //회원 탈퇴
    @PostMapping("/deleteUser")
    public RedirectView deleteAndSave(HttpServletRequest request, @RequestBody String reason) {

        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId"); //<=========================세션 userId
        String type = (String) session.getAttribute("type");
        WithdrawalDTO withdrawalDTO = new WithdrawalDTO();

        /*withdrawal테이블에 새로운값 추가*/
        withdrawalDTO.setCreatedDate(LocalDateTime.now());
        withdrawalDTO.setWithdrawalReason(reason);
        withdrawalDTO.setWithdrawalUserType("개인");

        //user서비스에서 type people,school확인하려구 만든거
        if (type.equals("people")) {
            withdrawalDTO.setWithdrawalUserType("일반");
        } else if (type.equals("school")) {
            withdrawalDTO.setWithdrawalUserType("보육원");
        }

        withdrawalService.insertReason(withdrawalDTO);
        userService.saveReasonAnddeleteUserID(userId); //<=========================세션 userId 넣어야됩니다.

        session.removeAttribute("userId");
        return new RedirectView("/main/main");
    }

}
