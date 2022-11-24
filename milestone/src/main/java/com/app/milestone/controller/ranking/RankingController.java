package com.app.milestone.controller.ranking;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ranking/*")
public class RankingController {

    @GetMapping("/ranking")
    public void ranking(){
    }

    @GetMapping("/myPageTest1")
    public void myPageTest1(){
    }

    @GetMapping("/myPageTest2")
    public void myPageTest2(){
    }

}
