package com.app.milestone.controller.admin;

import com.app.milestone.domain.Search;
import com.app.milestone.repository.SchoolRepository;
import com.app.milestone.service.SchoolService;
import com.app.milestone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/*")
public class AdminController {
    private final UserService userService;

    @GetMapping("talent")
    public void community(){};

    @GetMapping("money")
    public void history(){};

    @GetMapping("service")
    public void service(){};

    @GetMapping("notice")
    public void notice(){};

    @GetMapping("user")
    public void user(Model model, Pageable pageable){
        pageable = PageRequest.of(0, 7);
        model.addAttribute("users", userService.userList(pageable));
    };

    @GetMapping("school")
    public void evolution(){};

    @GetMapping("reason")
    public void reason(){};

}
