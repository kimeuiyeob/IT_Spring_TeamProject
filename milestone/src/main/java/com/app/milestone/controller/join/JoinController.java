package com.app.milestone.controller.join;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/join/*")
public class JoinController {

    @GetMapping("/user")
    public String user(){
        return "/join/joinUser";
    };

    @GetMapping("/school")
    public String school(){
        return "/join/joinSchool";
    };

    @GetMapping("/way")
    public String way(){
        return "/join/joinWay";
    };


}
