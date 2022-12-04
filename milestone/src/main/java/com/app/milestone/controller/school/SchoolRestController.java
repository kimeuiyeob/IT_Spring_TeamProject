package com.app.milestone.controller.school;

import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.domain.SchoolResp;
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

//    총 페이지도 같이 전달
    @GetMapping("/list/{schoolAddress}")
    public SchoolResp search(Pageable pageable, Search search, Model model) {
        SchoolResp schoolResp = new SchoolResp();
        pageable = PageRequest.of(0, 10);
        List<SchoolDTO> arSchoolDTO = schoolService.schoolList(pageable, search);
        if (search.getSchoolAddress() == null) {
            search.setSchoolAddress(new ArrayList<>());
        }

        schoolResp.setArSchoolDTO(arSchoolDTO);
        schoolResp.setTotal(schoolService.schoolListCount(pageable, search));
        return schoolResp;
    }
}
