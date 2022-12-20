package com.app.milestone.controller.login;

import com.app.milestone.domain.LoginDTO;
import com.app.milestone.entity.User;
import com.app.milestone.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
        if (user == null) {
            return new RedirectView("/main/main?login=false");
        } else {
            Long userId = user.getUserId();

            session.setAttribute("userId", userId);
            session.setAttribute("OAuth2", "google");

            if (userService.typeCheck(userId)) {
                session.setAttribute("type", "people");
            } else {
                session.setAttribute("type", "school");
            }
        }
        return new RedirectView("/main/main");
    }


    @GetMapping("/naver")
    public RedirectView naverLogin(@RequestParam String code, HttpSession session) throws Exception {
        String token = naverService.getNaverAccessToken(code);

        String password = naverService.getNaverProfileByToken(token).get(0) + "-naver";
        User user = googleJoinSupportService.PeopleDuplicated(password);
        if (user == null) {
            return new RedirectView("/main/main?login=false");
        } else {
            Long userId = user.getUserId();

            session.setAttribute("userId", userId);
            session.setAttribute("OAuth2", "naver");
            session.setAttribute("token", token);
            if (userService.typeCheck(userId)) {
                session.setAttribute("type", "people");
            } else {
                session.setAttribute("type", "school");
            }
        }
        return new RedirectView("/main/main");
    }

    @GetMapping("/naver-logout")
    public RedirectView naverLogout(HttpSession session) {
        String token = (String) session.getAttribute("token");

        naverService.logoutNaver(token);
        session.invalidate();

        return new RedirectView("/main/main");
    }

//    //    로그인페이지
//    @GetMapping("/login")
//    public String loginForm(@ModelAttribute("loginDTO") LoginDTO loginDTO) {
//        return "login/login";
//    }

    //    로그인페이지
    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginDTO") LoginDTO loginDTO, Long prevSchoolId, HttpServletRequest request) {
        log.info("===================로긴============");
        log.info("===================로긴============"+ prevSchoolId);
        log.info("===================로긴============");
        if(prevSchoolId != null){
            request.getSession().setAttribute("prevSchoolId",prevSchoolId);
        }
        return "login/login";
    }

    @GetMapping("/logout-google")
    public RedirectView googleLogout(HttpSession session) {
        session.invalidate();
        return new RedirectView("/main/main");
    }

//    //    로그인
//    // 서블릿 HTTP 세션 사용
//    @PostMapping("/login")
//    public String login(@Valid @ModelAttribute("loginDTO") LoginDTO loginDTO, BindingResult bindingResult, HttpServletRequest request) {
//        userService.login(loginDTO);
//
////        bindingResult.hasErrors() 값이 알맞은지 체크
//        if (bindingResult.hasErrors()) {
//            return "login/login";
//        }
//
//        Long userId = userService.login(loginDTO);
//
////        userId가 null이라면 아이디 또는 비밀번호 오류로 다시 로그인 페이지로 보내준다
//        if (userId == null) {
//            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
//            log.info("아이디 비번 오류");
//            return "login/login";
//        }
//
//        // 로그인 성공 처리
//        // 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성해서 반환
//        // getSession() : 디폴트가 true, false는 세션이 없을 때 새로 만들지 않고 null을 반환
//        HttpSession session = request.getSession();
//        // 세션에 로그인 회원 정보 보관
//        session.setAttribute("userId", userId);
//
////        user type구분
//        if (userService.typeCheck(userId)) {
//            session.setAttribute("type", "people");
//        } else {
//            session.setAttribute("type", "school");
//        }
//        if (userId == 1) {
//            session.setAttribute("type", "admin");
//        }
//
////        로그인 성공하면 main페이지로 보내준다
//        return "redirect:/main/main";
//    }

    //    로그인
    // 서블릿 HTTP 세션 사용
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginDTO") LoginDTO loginDTO, BindingResult bindingResult, HttpServletRequest request) {
        userService.login(loginDTO);

//        bindingResult.hasErrors() 값이 알맞은지 체크
        if (bindingResult.hasErrors()) {
            return "login/login";
        }

        Long userId = userService.login(loginDTO);

//        userId가 null이라면 아이디 또는 비밀번호 오류로 다시 로그인 페이지로 보내준다
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

//        user type구분
        if (userService.typeCheck(userId)) {
            session.setAttribute("type", "people");
        } else {
            session.setAttribute("type", "school");
        }
        if (userId == 1) {
            session.setAttribute("type", "admin");
        }
        
//        결제창으로 이동
        if(request.getSession().getAttribute("prevSchoolId") != null){
            return "redirect:/school/donation?userId="+request.getSession().getAttribute("prevSchoolId");
        }

//        로그인 성공하면 main페이지로 보내준다
        return "redirect:/main/main";
    }

    //    로그아웃
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();

        session = request.getSession(false);
//        session이 null이 아니면
        if (session != null) {
//            invalidate 세션을 없애고 세션에 속해있는 값들을 모두 없앤다.
            session.invalidate();
        }
//        로그아웃 성공 하면 main페이지로 보내준다
        return "redirect:/main/main";
    }

    //    로그아웃
    @GetMapping("/logout")
    public String logoutGet(HttpServletRequest request) {
        HttpSession session = request.getSession();

        session = request.getSession(false);
        if (session != null) {
//            invalidate 세션을 없애고 세션에 속해있는 값들을 모두 없앤다.
            session.invalidate();
        }
        return "redirect:/main/main";
    }

}
