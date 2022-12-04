package com.app.milestone.controller.admin;

import com.app.milestone.entity.School;
import com.app.milestone.service.SchoolService;
import com.app.milestone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/adminRest/*")
public class AdminRestController {
    private final SchoolService schoolService;
    private final UserService userService;

    @GetMapping("/user/{schools}")
    public List<School> clickOption(Model model, Pageable pageable){
        pageable = PageRequest.of(0,7);
        model.addAttribute("schools", schoolService.schoolListManager(pageable));
        model.addAttribute("users", userService.userList(pageable));

        return schoolService.schoolListManager(pageable);
    }
}
