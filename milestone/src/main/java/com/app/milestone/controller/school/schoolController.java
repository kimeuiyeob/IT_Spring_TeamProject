package com.app.milestone.controller.school;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/school/*")
public class schoolController {

    @GetMapping("/school-list")
    public void schoolList() {
    };

    @GetMapping("/school-help")
    public void schoolHelp() {
    };

    @GetMapping("/school-detail")
    public void schoolDetail() {
    };

    @GetMapping("/school-donation")
    public void schoolDonation() {
    };


}