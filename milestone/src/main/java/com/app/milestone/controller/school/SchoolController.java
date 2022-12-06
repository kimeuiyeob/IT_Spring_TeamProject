package com.app.milestone.controller.school;

import com.app.milestone.domain.Search;
import com.app.milestone.service.SchoolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/school/*")
@Slf4j
public class SchoolController {
    private final SchoolService schoolService;

    @GetMapping("/list")
    public void list(Search search ) {;}

    @GetMapping("/read")
    public void read(Long userId, Model model) {
        model.addAttribute("userId",userId);
        log.info(""+userId);
    }


//    ==============================================

    @GetMapping("/school-list")
    public void schoolList() {
    }

    ;

    @GetMapping("/help")
    public void schoolHelp() {
    }

    ;

    @GetMapping("/school-detail")
    public void schoolDetail() {
    }

    ;

    @GetMapping("/school-donation")
    public void schoolDonation() {
    }

    ;


}