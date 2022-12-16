package com.app.milestone.controller;

import com.app.milestone.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profile/*")
@Slf4j
public class ProfileController {
    private final FileService fileService;

    @GetMapping("/photo")
    public void main(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        model.addAttribute("fileDTO", fileService.showProfile(userId));
    }
}