package com.app.milestone.controller.school;

import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.domain.Search;
import com.app.milestone.service.SchoolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schoolrest/*")
@Slf4j
public class SchoolRestController {
    private final SchoolService schoolService;

    @GetMapping("/list/{schoolAddress}")
    public List<SchoolDTO> search(Pageable pageable, Search search, Model model) {
        log.info("========================================================================================ZXCZXCzXZXCz" + search);
        pageable = PageRequest.of(0, 10);
        if (search.getSchoolAddress() == null) {
            search.setSchoolAddress(new ArrayList<>());
        }
        model.addAttribute("schools", schoolService.schoolList(pageable, search));
        return schoolService.schoolList(pageable, search);
    }
}
