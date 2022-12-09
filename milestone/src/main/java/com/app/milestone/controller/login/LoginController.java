package com.app.milestone.controller.login;

import com.app.milestone.SessionConst;
import com.app.milestone.domain.LoginDTO;
import com.app.milestone.entity.User;
import com.app.milestone.service.PeopleService;
import com.app.milestone.service.SchoolService;
import com.app.milestone.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    //    로그인페이지
    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginDTO") LoginDTO loginDTO) {
        return "login/login";
    }

    // 서블릿 HTTP 세션 사용
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginDTO") LoginDTO loginDTO, BindingResult bindingResult, HttpServletRequest request) {
        log.info("로그인 컨트롤러");
        if (bindingResult.hasErrors()) {
            log.info("실패");
            return "login/login";
        }
        log.info("이메일: " + loginDTO.getUserEmail());
        log.info("pw: " + loginDTO.getUserPassword());
        User loginMember = userService.login(loginDTO.getUserEmail(), loginDTO.getUserPassword());
        log.info("유저 : " + loginMember);

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            log.info("아이디 비번 오류");
            return "login/login";
        }

        // 로그인 성공 처리
        // 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성해서 반환
        // getSession() : 디폴트가 true, false는 세션이 없을 때 새로 만들지 않고 null을 반환
        HttpSession session = request.getSession();
        // 세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        log.info("성공");
        log.info("sessionId={}", session.getId());
        log.info("getMaxInactiveInterval={}", session.getMaxInactiveInterval());
        log.info("creationTime={}", new Date(session.getCreationTime()));
        log.info("lastAccessedTime={}", new Date(session.getLastAccessedTime()));
        log.info("isNew={}", session.isNew());
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
            session.invalidate();   // 세션 날림
        }
        log.info("로그아웃 성공");
        return "redirect:/login/login";
    }

}
