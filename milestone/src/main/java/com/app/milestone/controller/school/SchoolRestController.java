package com.app.milestone.controller.school;

import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.domain.SchoolResp;
import com.app.milestone.domain.Search;
import com.app.milestone.service.SchoolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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

    //    보육원 목록
    @GetMapping(value = {"/list/{page}", "/list/{page}/{schoolAddress}", "/list/{page}/{schoolAddress}/{schoolName}"})
    public SchoolResp search(@PathVariable("page") Integer page, Search search, Model model) {
        SchoolResp schoolResp = new SchoolResp();
        Pageable pageable = PageRequest.of(page, 10);
        Page<SchoolDTO> arSchoolDTO = schoolService.schoolList(page, search);
        schoolResp.setArSchoolDTO(arSchoolDTO);
        schoolResp.setTotal(schoolService.schoolListCount(pageable, search));
        return schoolResp;
    }

    //    보육원 상세페이지
    @GetMapping(value = {"/read/{userId}"})
    public SchoolDTO search(@PathVariable("userId") Long userId) {
        log.info("===============아작아작"+schoolService.schoolInfo(userId));
        return schoolService.schoolInfo(userId);
    }
}
