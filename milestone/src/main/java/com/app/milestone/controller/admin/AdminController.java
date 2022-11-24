package com.app.milestone.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/*")
public class AdminController {
    @GetMapping("talent")
    public void community(){};

    @GetMapping("money")
    public void history(){};

    @GetMapping("service")
    public void service(){};

    @GetMapping("notice")
    public void notice(){};

    @GetMapping("statistics")
    public void statistics(){};

    @GetMapping("user")
    public void user(){};

    @GetMapping("evolution")
    public void evolution(){};

    @GetMapping("reason")
    public void reason(){};

}
