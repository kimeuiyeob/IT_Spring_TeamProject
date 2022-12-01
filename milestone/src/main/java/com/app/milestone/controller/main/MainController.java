package com.app.milestone.controller.main;

import com.app.milestone.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/main/*")
public class MainController {
    private final SchoolService schoolService;

    @GetMapping("main")
    public void main(Model model) {
//        도움이 필요한 보육원
        model.addAttribute("schools", schoolService.needHelpList());
    }

}