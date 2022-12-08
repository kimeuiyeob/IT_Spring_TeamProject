package com.app.milestone.controller.school;

import com.app.milestone.domain.MoneyDTO;
import com.app.milestone.domain.Search;
import com.app.milestone.service.SchoolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/school/*")
@Slf4j
public class SchoolController {
    private final SchoolService schoolService;

    //    보육원 목록
    @GetMapping("/list")
    public void list(Search search) {
        ;
    }

    //    보육원 상세
    @GetMapping("/read")
    public void read(Long userId, Model model) {
        model.addAttribute("userId", userId);
    }

    //    ======================결제==========================
    //    결제페이지 이동
    @GetMapping("/donation")
    public void donation(Long userId, Model model) {
        model.addAttribute("schoolDTO", schoolService.schoolInfo(userId));
    }

    //    결제진행
    @PostMapping("/payment")
    public RedirectView payment(@RequestBody MoneyDTO moneyDTO) {
        log.info("==============asd==================="+moneyDTO);
//        replyService.register(replyDTO);
        return new RedirectView("/school/read");
    }

}