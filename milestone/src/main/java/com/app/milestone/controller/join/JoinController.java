package com.app.milestone.controller.join;

import com.app.milestone.domain.PeopleDTO;
import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.service.CertificationService;
import com.app.milestone.service.GoogleJoinService;
import com.app.milestone.service.PeopleService;
import com.app.milestone.service.SchoolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @GetMapping("/user")
    public String createPeople(Model model) {
        model.addAttribute("peopleDTO", new PeopleDTO());
        return "join/joinUser";
    }

    @GetMapping("/school")
    public String createSchool(Model model) {
        model.addAttribute("schoolDTO", new SchoolDTO());
        return "join/joinSchool";
    }

    @GetMapping("/OAuth")
    public String createOAuth(Model model, HttpServletRequest request, HttpSession session) {
        model.addAttribute("peopleDTO", new PeopleDTO());
        if (session.getId() != null) {
            return "join/joinOAuth";
        }
        return "redirect:/kakao/logout";
    }

    @GetMapping("/way")
    public String way() {
        return "/join/joinWay";
    }

    ;

    @GetMapping("/logout")
    public String logout() {
        return "/join/logout";
    }

    ;

    @GetMapping("/select")
    public String select() {
        return "/join/joinSelect";
    }

    ;

    @GetMapping("/joinOauth")
    public String joinOauth(PeopleDTO peopleDTO) {
        return "join/joinOAuth";
    }


    @GetMapping("/phoneCheck")
    @ResponseBody
    public String sendSMS(@RequestParam("phone") String userPhoneNumber) { // 휴대폰 문자보내기
        int randomNumber = (int) ((Math.random() * (9999 - 1000 + 1)) + 1000);//난수 생성

        certificationService.certifiedPhoneNumber(userPhoneNumber, randomNumber);

        return Integer.toString(randomNumber);
    }

    //구글 오어스 회원가입
    @GetMapping("/google")
    public RedirectView googleJoin(@RequestParam String code, RedirectAttributes redirectAttributes) throws Exception {
        ArrayList<String> list = googleJoinService.getGoogleAccessTokenInfo(code);

        String email = list.get(0);
        String name = list.get(1);
        String password = list.get(2)+"-google";
        redirectAttributes.addFlashAttribute("join", "google");
        redirectAttributes.addFlashAttribute("userEmail", email);

        redirectAttributes.addFlashAttribute("userName", name);

        redirectAttributes.addFlashAttribute("userPassword", password);

        return new RedirectView("/join/joinOauth");
    }

}
