package com.app.milestone.controller.talent;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/talent/*")
public class TalentController {

    @GetMapping("/talent")
    public String talent() {
        return "/talent/talent";
    }
    ;
}
