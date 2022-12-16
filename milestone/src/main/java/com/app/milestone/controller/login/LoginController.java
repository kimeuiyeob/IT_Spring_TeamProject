package com.app.milestone.controller.login;

import com.app.milestone.domain.LoginDTO;
import com.app.milestone.entity.User;
import com.app.milestone.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/login/*")
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final PeopleService peopleService;
    private final SchoolService schoolService;
    private final UserService userService;
    private final GoogleJoinSupportService googleJoinSupportService;
    private final GoogleJoinService googleJoinService;
    private final NaverService naverService;

    @GetMapping("/google")
    public RedirectView googleLogin(@RequestParam String code, HttpSession session) throws Exception {
        String password = googleJoinService.loginInfo(code) + "-google";
        User user = googleJoinSupportService.PeopleDuplicated(password);
        if(user == null) {
            return new RedirectView("/main/main?login=false");
        }else{
            Long userId = user.getUserId();

            session.setAttribute("userId", userId);

            if(userService.typeCheck(userId)){
                session.setAttribute("type", "people");
            }else {
                session.setAttribute("type", "school");
            }
        }
        return new RedirectView("/main/main");
    }


    @GetMapping("/naver")
    public RedirectView naverLogin(@RequestParam String code, HttpSession session) throws Exception {
        String token = naverService.getNaverAccessToken(code);

        String password = naverService.getNaverProfileByToken(token).get(0)+"-naver";
        User user = googleJoinSupportService.PeopleDuplicated(password);
        if(user == null) {
            return new RedirectView("/main/main?login=false");
        }else{
            Long userId = user.getUserId();

            session.setAttribute("userId", userId);
            session.setAttribute("OAuth2","naver");
            session.setAttribute("token",token);
            if(userService.typeCheck(userId)){
                session.setAttribute("type", "people");
            }else {
                session.setAttribute("type", "school");
            }
        }
        return new RedirectView("/main/main");
    }

    @GetMapping("/naver-logout")
    public RedirectView naverLogout(HttpSession session){
        String token= (String)session.getAttribute("token");

        naverService.logoutNaver(token);
        session.invalidate();

        return new RedirectView("/main/main");
    }

    //    로그인페이지
    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginDTO") LoginDTO loginDTO) {
        return "login/login";
    }

    // 서블릿 HTTP 세션 사용
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginDTO") LoginDTO loginDTO, BindingResult bindingResult, HttpServletRequest request) {
        userService.login(loginDTO);
        log.info("로그인 컨트롤러");
        if (bindingResult.hasErrors()) {
            log.info("실패");
            return "login/login";
        }
        log.info("이메일: " + loginDTO.getUserEmail());
        log.info("pw: " + loginDTO.getUserPassword());

        Long userId = userService.login(loginDTO);

        if (userId == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            log.info("아이디 비번 오류");
            return "login/login";
        }

        // 로그인 성공 처리
        // 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성해서 반환
        // getSession() : 디폴트가 true, false는 세션이 없을 때 새로 만들지 않고 null을 반환
        HttpSession session = request.getSession();
        // 세션에 로그인 회원 정보 보관
        session.setAttribute("userId", userId);

        if(userService.typeCheck(userId)){
            session.setAttribute("type", "people");
        }else {
            session.setAttribute("type", "school");
        }
        if(userId==1){
            session.setAttribute("type", "admin");
        }

//        log.info("유저 : " + loginMember.getUserEmail());
//        log.info("유저 : " + loginMember.getUserEmail());
//        log.info("성공");
//        log.info("sessionId={}", session.getId());
//        log.info("getMaxInactiveInterval={}", session.getMaxInactiveInterval());
//        log.info("creationTime={}", new Date(session.getCreationTime()));
//        log.info("lastAccessedTime={}", new Date(session.getLastAccessedTime()));
//        log.info("isNew={}", session.isNew());
        return "redirect:/main/main";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();

        log.info("sessionId={}", session.getId());
        log.info("getMaxInactiveInterval={}", session.getMaxInactiveInterval());
        log.info("creationTime={}", new Date(session.getCreationTime()));
        log.info("lastAccessedTime={}", new Date(session.getLastAccessedTime()));
        log.info("isNew={}", session.isNew());

        session = request.getSession(false);
        if (session != null) {
            log.info("세션 날림");
            session.invalidate();   // 세션 날림
        }
        log.info("sessionId={}", session.getId());
        log.info("로그아웃 성공");
        return "redirect:/main/main";
    }
    @GetMapping("/logout")
    public String logoutGet(HttpServletRequest request) {
        HttpSession session = request.getSession();

        log.info("sessionId={}", session.getId());
        log.info("getMaxInactiveInterval={}", session.getMaxInactiveInterval());
        log.info("creationTime={}", new Date(session.getCreationTime()));
        log.info("lastAccessedTime={}", new Date(session.getLastAccessedTime()));
        log.info("isNew={}", session.isNew());

        session = request.getSession(false);
        if (session != null) {
            log.info("세션 날림");
            session.invalidate();   // 세션 날림
        }
        log.info("sessionId={}", session.getId());
        log.info("로그아웃 성공");
        return "redirect:/main/main";
    }

}
