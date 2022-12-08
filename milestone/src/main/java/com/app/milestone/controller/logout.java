package com.app.milestone.controller;

import com.app.milestone.domain.PeopleDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/logout/*")
@RequiredArgsConstructor
@Slf4j
public class logout {

    @GetMapping("/logout")
    public String kakaoLogout() {
        return "redirect:/logout";
    }
}
