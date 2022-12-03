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

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
@RequestMapping("/school/*")
@Slf4j
public class SchoolController {
    private final SchoolService schoolService;

    @GetMapping("/list")
    public void list(Pageable pageable, Search search, Model model) {
        pageable = PageRequest.of(0, 10);
        if (search.getSchoolAddress() == null) {
            search.setSchoolAddress(new ArrayList<>());
            search.getSchoolAddress().add(null);
            search.getSchoolAddress().add(null);
            search.getSchoolAddress().add(null);
            search.getSchoolAddress().add(null);
            search.getSchoolAddress().add(null);
            search.getSchoolAddress().add(null);
            search.getSchoolAddress().add(null);
            search.getSchoolAddress().add(null);
        }
        log.info("=================================================================="+schoolService.schoolList(pageable, search));
        model.addAttribute("schools", schoolService.schoolList(pageable, search));
        model.addAttribute("totalSchool", schoolService.schoolList(pageable, search).size());
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