package com.app.milestone.controller.school;

import com.app.milestone.domain.Search;
import com.app.milestone.service.SchoolService;
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
@RequestMapping("/school/*")
public class SchoolController {
    private final SchoolService schoolService;

    @GetMapping("/list")
    public void list(Pageable pageable, Search search, Model model) {
        pageable = PageRequest.of(0, 10);
        if (search.getSchoolAddress() == null) {
            search.setSchoolAddress(new ArrayList<>());
        }
        model.addAttribute("schools", schoolService.schoolList(pageable, search));
    }


//    ==============================================

    @GetMapping("/school-list")
    public void schoolList() {
    }

    ;

    @GetMapping("/school-help")
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