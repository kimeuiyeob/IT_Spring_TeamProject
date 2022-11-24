package com.app.milestone.controller.introduce;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/introduce/*")
public class IntroduceController {

    @GetMapping("/introduce")
    public void introduce(){
    }

    @GetMapping("/schoolSignUp")
    public void schoolSignUp(){
    }

}
