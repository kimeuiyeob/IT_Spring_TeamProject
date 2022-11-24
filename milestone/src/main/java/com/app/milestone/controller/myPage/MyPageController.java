package com.app.milestone.controller.myPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage/*")
public class MyPageController {

        @GetMapping("/myinfo")
    public String myInfo() {
        return "/myPage/myPage_myInfo";
    };

    @GetMapping("/password")
    public String password() {
        return "/myPage/myPage_password";
    };

    @GetMapping("/schedule")
    public String schedule() {
        return "/myPage/myPage_schedule";
    };

    @GetMapping("/schoolinfo")
    public String schoolInfo() {
        return "/myPage/myPage_schoolInfo";
    };

    @GetMapping("/likelist")
    public String likeList() {
        return "/myPage/myPage_likeList";
    };

    @GetMapping("/withdrawal")
    public String withdrawal() {
        return "/myPage/myPage_withdrawal";
    };
}
