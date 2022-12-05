package com.app.milestone.controller.join;

import com.app.milestone.domain.PeopleDTO;
import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.service.PeopleService;
import com.app.milestone.service.SchoolService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/join/*")
public class JoinController {
    private PeopleService peopleService;
    private SchoolService schoolService;

    @GetMapping("/user")
    public String user() {
        return "/join/joinUser";
    }

    ;

    @GetMapping("/school")
    public String school() {
        return "/join/joinSchool";
    }

    ;

    @GetMapping("/way")
    public String way() {
        return "/join/joinWay";
    }

    ;

    @GetMapping("/select")
    public String select() {
        return "/join/joinSelect";
    }

    ;

    @PostMapping("/user")
    public ResponseEntity PeopleSignUp(@RequestBody PeopleDTO peopleDTO) {
        return peopleService.peopleSignUp(peopleDTO);
    }

//    @PostMapping("/school")
//    public ResponseEntity schoolSignUp(@RequestBody SchoolDTO schoolDTO) {
//        return schoolService.schoolSignUp(schoolDTO);
//    }

}
