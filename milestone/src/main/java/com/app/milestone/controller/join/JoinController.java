package com.app.milestone.controller.join;

import com.app.milestone.domain.PeopleDTO;
import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.service.KakaoService;
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

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/join/*")
@RequiredArgsConstructor
@Slf4j
public class JoinController {
    private final PeopleService peopleService;
    private final SchoolService schoolService;
    private final KakaoService kakaoService;

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

    ;

    @GetMapping("/way")
    public String way() {
        return "/join/joinWay";
    }

    ;

    @GetMapping("/select")
    public String select() {
        return "/join/joinSelect";
    }

    ;

    @ResponseBody
    @GetMapping("/login/kakao")
    public void kakaoCallback(@RequestParam String code, HttpSession session) throws Exception {
        log.info(code);
        String token = kakaoService.getKaKaoAccessToken(code);
        session.setAttribute("token", token);
        kakaoService.getKakaoInfo(token);
    }

}
