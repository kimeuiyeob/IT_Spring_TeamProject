package com.app.milestone.controller.notice;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notice/*")
public class NoticeController {


    @GetMapping(value = {"notice", "notice1", "notice2", "notice3", "notice4", "notice5", "notice6"})
    public void notice(){
    }

}
