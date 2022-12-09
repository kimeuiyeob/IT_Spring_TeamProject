package com.app.milestone.controller.join;

import com.app.milestone.domain.PeopleDTO;
import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.service.PeopleService;
import com.app.milestone.service.SchoolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/join/*")
@RequiredArgsConstructor
@Slf4j
public class JoinController {
    private final PeopleService peopleService;
    private final SchoolService schoolService;

    @GetMapping("/user")
    public String createPeople(Model model) {
        model.addAttribute("peopleDTO", new PeopleDTO());
        return "join/joinUser";
    }

    @GetMapping("/school")
    public String createSchool(Model model) {
        model.addAttribute("schoolDTO", new SchoolDTO());
        return "join/joinSchool";
    }

    @GetMapping("/OAuth")
    public String createOAuth(Model model) {
        model.addAttribute("peopleDTO", new PeopleDTO());
        return "join/joinOAuth";
    }

    @GetMapping("/way")
    public String way() {
        return "/join/joinWay";
    }

    ;

    @GetMapping("/logout")
    public String logout() {
        return "/join/logout";
    }

    ;

    @GetMapping("/select")
    public String select() {
        return "/join/joinSelect";
    }

    ;


}
