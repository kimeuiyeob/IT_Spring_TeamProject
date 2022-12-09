package com.app.milestone.controller.admin;

import com.app.milestone.domain.*;
import com.app.milestone.entity.Withdrawal;
import com.app.milestone.service.PeopleService;
import com.app.milestone.service.SchoolService;
import com.app.milestone.service.UserService;
import com.app.milestone.service.WithdrawalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/adminRest/*")
public class AdminRestController {
    private final SchoolService schoolService;
    private final PeopleService peopleService;
    private final UserService userService;
    private final WithdrawalService withdrawalService;


//    전체회원에서 일반회원 선택 후 검색 및 조회(기본 : 최신순)
    @GetMapping(value= {"/userpeople/{page}", "/userpeople/{page}/{peopleNickName}","/userpeople/{page}/{peopleNickName}/{userName}"})
    public PeopleResp userListPeople(@PathVariable("page") Integer page, Search search, Model model) {
        PeopleResp peopleResp = new PeopleResp();
        Pageable pageable = PageRequest.of(page, 7);
        Page<PeopleDTO> arPeopleDTO = peopleService.peopleListSearch(page, search);
        peopleResp.setArPeopleDTO(arPeopleDTO);
        peopleResp.setTotal(peopleService.peopleListCount(pageable, search));
        return peopleResp;
    }

//    전체회원에서 일반회원 선택 후 검색 및 조회 (asc)
    @GetMapping(value= {"/userpeopleAsc/{page}", "/userpeopleAsc/{page}/{peopleNickName}","/userpeopleAsc/{page}/{peopleNickName}/{userName}"})
    public PeopleResp userListPeopleAsc(@PathVariable("page") Integer page, Search search, Model model) {
        PeopleResp peopleResp = new PeopleResp();
        Pageable pageable = PageRequest.of(page, 7);
        Page<PeopleDTO> arPeopleDTO = peopleService.peopleListSearchAsc(page, search);
        peopleResp.setArPeopleDTO(arPeopleDTO);
        peopleResp.setTotal(peopleService.peopleListCount(pageable, search));
        return peopleResp;
    }

//    전체회원에서 보육원 선택후 검색 및 조회 (기본 : 최신순)
    @GetMapping(value= {"/userschool/{page}", "/userschool/{page}/{schoolName}","/userschool/{page}/{schoolName}/{userName}"})
    public SchoolResp userListSchool(@PathVariable("page") Integer page, Search search, Model model) {
        SchoolResp schoolResp = new SchoolResp();
        Pageable pageable = PageRequest.of(page, 7);
        Page<SchoolDTO> arSchoolDTO = schoolService.schoolListSearch(page, search);
        schoolResp.setArSchoolDTO(arSchoolDTO);
        schoolResp.setTotal(schoolService.schoolListCount2(pageable, search));
        return schoolResp;
    }

//    전체회원에서 보육원 선택후 검색 및 조회 (asc)
    @GetMapping(value= {"/userschoolAsc/{page}", "/userschoolAsc/{page}/{schoolName}","/userschoolAsc/{page}/{schoolName}/{userName}"})
    public SchoolResp userListSchoolAsc(@PathVariable("page") Integer page, Search search, Model model) {
        SchoolResp schoolResp = new SchoolResp();
        Pageable pageable = PageRequest.of(page, 7);
        Page<SchoolDTO> arSchoolDTO = schoolService.schoolListSearchAsc(page, search);
        schoolResp.setArSchoolDTO(arSchoolDTO);
        schoolResp.setTotal(schoolService.schoolListCount2(pageable, search));
        return schoolResp;
    }


//    보육원 회원 조회
    @GetMapping(value= {"/school/{page}", "/school/{page}/{schoolAddress}","/school/{page}/{schoolAddress}/{schoolName}"})
    public SchoolResp schoolList(@PathVariable("page") Integer page, Search search, Model model) {
        SchoolResp schoolResp = new SchoolResp();
        Pageable pageable = PageRequest.of(page, 7);
        Page<SchoolDTO> arSchoolDTO = schoolService.schoolOnly(page,search);
        schoolResp.setArSchoolDTO(arSchoolDTO);
        schoolResp.setTotal(schoolService.schoolListCount(pageable, search));
        return schoolResp;
    }

//    보육원회원에서 검색 및 예산 내림차순
    @GetMapping(value= {"/schoolbudget/{page}", "/schoolbudget/{page}/{schoolAddress}","/schoolbudget/{page}/{schoolAddress}/{schoolName}"})
    public SchoolResp schoolListBudget(@PathVariable("page") Integer page, Search search, Model model) {
        SchoolResp schoolResp = new SchoolResp();
        Pageable pageable = PageRequest.of(page, 7);
        Page<SchoolDTO> arSchoolDTO = schoolService.schoolListBudgetSearch(page, search);
        schoolResp.setArSchoolDTO(arSchoolDTO);
        schoolResp.setTotal(schoolService.schoolListCount(pageable, search));
        return schoolResp;
    }

//    보육원회원에서 검색 및 예산 오름차순
    @GetMapping(value= {"/schoolbudgetAsc/{page}", "/schoolbudgetAsc/{page}/{schoolAddress}","/schoolbudgetAsc/{page}/{schoolAddress}/{schoolName}"})
    public SchoolResp schoolListBudgetAsc(@PathVariable("page") Integer page, Search search, Model model) {
        SchoolResp schoolResp = new SchoolResp();
        Pageable pageable = PageRequest.of(page, 7);
        Page<SchoolDTO> arSchoolDTO = schoolService.schoolListBudgetSearchAsc(page, search);
        schoolResp.setArSchoolDTO(arSchoolDTO);
        schoolResp.setTotal(schoolService.schoolListCount(pageable, search));
        return schoolResp;
    }

    //   전체회원 중 일반회원 삭제
    @RequestMapping("/peopleDelete")
    public void deletePeople(HttpServletRequest request){
        String [] userIds = request.getParameterValues("chkArray");
        for (int i = 0; i<userIds.length; i++){
            peopleService.deleteByUserId(Long.valueOf(userIds[i]));
        }
//        for(Long userId : userIds) peopleService.deleteByUserId(Long.valueOf(userId));
    }


    //   보육원회원 삭제
    @RequestMapping("/schoolDelete")
    public void deleteSchool(HttpServletRequest request){
        String [] userIds = request.getParameterValues("chkArray");
        for (int i = 0; i<userIds.length; i++){
            schoolService.deleteByUserId(Long.valueOf(userIds[i]));
        }
    }


    //    탈퇴회원 조회
    @GetMapping(value= {"/withdrawal/{page}", "/withdrawal/{page}/{withdrawalReason}"})
    public WithdrawalResp witthdrawal(@PathVariable("page") Integer page, Search search, Model model) {
        WithdrawalResp withdrawalResp = new WithdrawalResp();
        Pageable pageable = PageRequest.of(page, 7);
        Page<WithdrawalDTO> arWithdrawalDTO = withdrawalService.withdrawalListSearch(page, search);
        withdrawalResp.setArWithdrawalDTO(arWithdrawalDTO);
        withdrawalResp.setTotal(withdrawalService.withdrawalListCount(pageable, search));
        return withdrawalResp;
    }

    //    탈퇴회원 조회
    @GetMapping(value= {"/withdrawalAsc/{page}", "/withdrawalAsc/{page}/{withdrawalReason}"})
    public WithdrawalResp witthdrawalAsc(@PathVariable("page") Integer page, Search search, Model model) {
        WithdrawalResp withdrawalResp = new WithdrawalResp();
        Pageable pageable = PageRequest.of(page, 7);
        Page<WithdrawalDTO> arWithdrawalDTO = withdrawalService.withdrawalListSearchAsc(page, search);
        withdrawalResp.setArWithdrawalDTO(arWithdrawalDTO);
        withdrawalResp.setTotal(withdrawalService.withdrawalListCount(pageable, search));
        return withdrawalResp;
    }

}
