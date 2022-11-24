package com.app.milestone.controller.find;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/find/*")
public class FindController {

    @GetMapping("select")
    public void select(){};

    @GetMapping("email")
    public void email(){};

    @GetMapping("password")
    public void password(){};


}
