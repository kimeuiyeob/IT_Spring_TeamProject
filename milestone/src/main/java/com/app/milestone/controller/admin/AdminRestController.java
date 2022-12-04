package com.app.milestone.controller.admin;

import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.entity.School;
import com.app.milestone.service.SchoolService;
import com.app.milestone.service.UserService;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/adminRest/*")
public class AdminRestController {
    private final SchoolService schoolService;
    private final UserService userService;

//    전체회원에서 보육원회원만 조회하기
    @PostMapping("/schools")
//    public List<Tuple> showOnlySchools(){ return schoolService.schoolOnly();}
    public List<SchoolDTO> showOnlySchools(){ return schoolService.schoolOnly();
    }
}
