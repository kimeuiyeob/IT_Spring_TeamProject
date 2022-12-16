package com.app.milestone.controller.introduce;

import com.app.milestone.service.IntroduceService;
import com.app.milestone.service.PeopleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/introduce/*")
public class IntroduceController {

    private final IntroduceService introduceService;


    //소개페이지
    @GetMapping("/introduce")
    public void introduce(Model model) {
        model.addAttribute("people", introduceService.PeopleCount());
        model.addAttribute("school", introduceService.SchoolCount());
        model.addAttribute("donation", introduceService.DonationCount());
    }


    //보육원 회원가입시 나오는 페이지
    @GetMapping("/schoolSignUp")
    public void schoolSignUp(Model model) {
        model.addAttribute("people", introduceService.PeopleCount());
        model.addAttribute("school", introduceService.SchoolCount());
        model.addAttribute("donation", introduceService.DonationCount());

        log.info("값있니1? : " + introduceService.PeopleCount());
        log.info("값있니2? : " + introduceService.SchoolCount());
        log.info("값있니3? : " + introduceService.DonationCount());
    }

}
