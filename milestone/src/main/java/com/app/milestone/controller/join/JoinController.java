package com.app.milestone.controller.join;

import com.app.milestone.domain.PeopleDTO;
import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.entity.People;
import com.app.milestone.repository.PeopleRepository;
import com.app.milestone.repository.UserCustomRepository;
import com.app.milestone.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@RequestMapping("/join/*")
@RequiredArgsConstructor
@Slf4j
public class JoinController {
    private final PeopleService peopleService;
    private final SchoolService schoolService;
    private final CertificationService certificationService;
    private final GoogleJoinService googleJoinService;
    private final PeopleRepository peopleRepository;
    private final GoogleJoinSupportService googleJoinSupportService;
    private final NaverService naverService;
    private final UserService userService;

//    일반 회원 회원가입
    @GetMapping("/user")
    public String createPeople(Model model) {
        model.addAttribute("peopleDTO", new PeopleDTO());
        return "join/joinUser";
    }
//    보육원 회원 회원가입
    @GetMapping("/school")
    public String createSchool(Model model) {
        model.addAttribute("schoolDTO", new SchoolDTO());
        return "join/joinSchool";
    }
//    카카오 회원가입
    @GetMapping("/OAuth")
    public String createOAuth(Model model, HttpServletRequest request, HttpSession session) {
        model.addAttribute("peopleDTO", new PeopleDTO());
        if (session.getId() != null) {
            return "join/joinOAuth";
        }
        return "redirect:/kakao/logout";
    }
//    이메일 중복검사
    @PostMapping(value = {"/checkEmail"})
    public Long checkEmail(@RequestBody String userEmail) {
        return userService.checkEmail(userEmail);
    }
//    일반회원 가입 종류 선택
    @GetMapping("/way")
    public String way() {
        return "/join/joinWay";
    }

    ;
//    로그아웃
    @GetMapping("/logout")
    public String logout() {
        return "/join/logout";
    }

    ;
//    보육원가입인지 일반 가입인지 선택 창
    @GetMapping("/select")
    public String select() {
        return "/join/joinSelect";
    }

    ;
//    OAuth로 이메일 받아서 가입하는 화면
    @GetMapping("/joinOauth")
    public String joinOauth(PeopleDTO peopleDTO) {
        return "join/joinOAuth";
    }

//    휴대폰 인증 문자 발송
    @GetMapping("/phoneCheck")
    @ResponseBody
    public String sendSMS(@RequestParam("phone") String userPhoneNumber) { // 휴대폰 문자보내기
        int randomNumber = (int) ((Math.random() * (9999 - 1000 + 1)) + 1000);//난수 생성

        certificationService.certifiedPhoneNumber(userPhoneNumber, randomNumber);

        return Integer.toString(randomNumber);
    }

    @GetMapping("/join")
    public String joinGoogle() {

        PeopleDTO peopleDTO = new PeopleDTO();
//
//        peopleDTO.setUserEmail(email);
//        peopleDTO.setUserPassword(password);
//        peopleDTO.setUserName(name);
//        peopleDTO.setPeopleNickname(name);
//        peopleDTO.setUserPhoneNumber("01012345678");
//        peopleDTO.setDonationCount(0);
//
//
//        People people = peopleDTO.toEntity();
//        peopleRepository.save(people);
        return "/join/joinGooglePhone";
    }


    @GetMapping("/joinOK")
    public RedirectView joinGoogle(PeopleDTO peopleDTO){
        peopleDTO.setDonationCount(0);

        People people = peopleDTO.toEntity();
        peopleRepository.save(people);

        return new RedirectView("/main/main?join=true");
    }


    //구글 오어스 회원가입
    @GetMapping("/google")
    public RedirectView googleJoin(@RequestParam String code, RedirectAttributes redirectAttributes) throws Exception {
        ArrayList<String> list = googleJoinService.getGoogleAccessTokenInfo(code);

        String email = list.get(0);
        String name = list.get(1);
        String password = list.get(2) + "-google";

        redirectAttributes.addFlashAttribute("email",email);
        redirectAttributes.addFlashAttribute("name",name);
        redirectAttributes.addFlashAttribute("password",password);

        if (googleJoinSupportService.PeopleDuplicated(password) == null) {
                return new RedirectView("/join/join");
        } else {
            return new RedirectView("/main/main?join=false");
        }
    }

    // 네이버
    @GetMapping("/naver")
    public RedirectView naverJoin(@RequestParam String code, RedirectAttributes redirectAttributes) throws Exception {
        String token = naverService.getNaverAccessToken(code);
        ArrayList<String> infos = naverService.getNaverProfileByToken(token);
        String oAuthId = infos.get(0)+"-naver";
        String name = infos.get(1);
        String phone = infos.get(2);
        String email = infos.get(3);



        log.info("이메일 : " +email);



        if(googleJoinSupportService.PeopleDuplicated(oAuthId) == null){
            PeopleDTO peopleDTO = new PeopleDTO();

            peopleDTO.setUserEmail(email);
            peopleDTO.setUserPassword(oAuthId);
            peopleDTO.setUserName(name);
            peopleDTO.setPeopleNickname(name);
            peopleDTO.setUserPhoneNumber(phone);
            peopleDTO.setDonationCount(0);
            People people = peopleDTO.toEntity();
            peopleRepository.save(people);
        }else{
            return new RedirectView("/main/main?join=false");
        }

        return new RedirectView("/main/main?join=true");
    }
}