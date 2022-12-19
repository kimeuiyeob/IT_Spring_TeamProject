package com.app.milestone.controller.join;

import com.app.milestone.service.KakaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/kakao/*")
@RequiredArgsConstructor
@Slf4j
public class KakaoController {
    private final KakaoService kakaoService;

//    카카오 회원가입, 토큰을 받아옴
    @ResponseBody
    @GetMapping("/login")
    public RedirectView kakaoCallback(@RequestParam String code, HttpSession session, RedirectAttributes redirectAttributes) throws Exception {
        log.info(code);
        String token = kakaoService.getKaKaoAccessToken(code);
        session.setAttribute("token", token);
        kakaoService.getKakaoInfo(token);
        redirectAttributes.addAttribute("userEmail", kakaoService.getKakaoInfo(token));
        return new RedirectView("/join/OAuth");
    }
//    카카오 로그아웃, 세션을 날려버린다
    @GetMapping("/logout")
    public RedirectView kakaoLogout(HttpSession session) {
        log.info("logout");
        kakaoService.logoutKakao((String) session.getAttribute("token"));
        session.invalidate();
        return new RedirectView("/join/OAuth");
    }
}
