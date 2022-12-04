package com.app.milestone.controller.admin;

import com.app.milestone.domain.Search;
import com.app.milestone.repository.SchoolRepository;
import com.app.milestone.service.SchoolService;
import com.app.milestone.service.UserService;
import com.app.milestone.service.WithdrawalService;
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
    private final WithdrawalService withdrawalService;
    private final SchoolService schoolService;

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
    public void school(Pageable pageable, Model model){
        pageable = PageRequest.of(0,7);
        model.addAttribute("schools", schoolService.schoolListManager(pageable));
    };

    @GetMapping("reason")
    public void reason(Pageable pageable,Model model){
        pageable = PageRequest.of(0, 7);
        model.addAttribute("withdrawals", withdrawalService.withdrawalList(pageable));
    };

}
