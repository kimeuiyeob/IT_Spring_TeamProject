package com.app.milestone.controller.admin;

import com.app.milestone.domain.PeopleDTO;
import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.domain.WithdrawalDTO;
import com.app.milestone.entity.School;
import com.app.milestone.entity.User;
import com.app.milestone.entity.Withdrawal;
import com.app.milestone.service.PeopleService;
import com.app.milestone.service.SchoolService;
import com.app.milestone.service.UserService;
import com.app.milestone.service.WithdrawalService;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/adminRest/*")
public class AdminRestController {
    private final SchoolService schoolService;
    private final PeopleService peopleService;
    private final UserService userService;
    private final WithdrawalService withdrawalService;

//    전체회원 조회하기
    @PostMapping("/all")
//    public List<Tuple> showOnlySchools(){ return schoolService.schoolOnly();}
    public List<User> showAll(Pageable pageable){
        pageable = PageRequest.of(0,7);

        return userService.userList(pageable);
    }

//    전체회원에서 보육원회원만 조회하기
    @PostMapping("/schools")
//    public List<Tuple> showOnlySchools(){ return schoolService.schoolOnly();}
    public List<SchoolDTO> showOnlySchools(){ return schoolService.schoolOnly();
    }

//    전체회원에서 일반회원만 조회하기
    @PostMapping("/people")
    public List<PeopleDTO> showOnlyPeople(){ return peopleService.peopleOnly();
    }

//    일반회원에서 오름차순
    @PostMapping("/peopleasc")
    public List<PeopleDTO> showPeopleListAsc(){
        return peopleService.peopleOnlyAsc();
    }

//    보육원회원에서 최신순
    @PostMapping("/school")
    public List<SchoolDTO> showSchoolList(){
        return schoolService.schoolOnly();
    }

//    보육원회원에서 오름차순
    @PostMapping("/schoolasc")
    public List<SchoolDTO> showSchoolListAsc(){
        return schoolService.schoolOnlyAsc();
    }


//    탈퇴회원 조회
    @PostMapping("/withdrawal")
    public List<WithdrawalDTO> showWithdrawalList(Pageable pageable){
        pageable = PageRequest.of(0,7);
        return withdrawalService.withdrawalList(pageable);
    }

//    탈퇴회원 조회 (오름차순)
    @PostMapping("/withdrawalAsc")
    public List<WithdrawalDTO> showWithdrawalListAsc(){
        return withdrawalService.withdrawalListAsc();
    }


}
