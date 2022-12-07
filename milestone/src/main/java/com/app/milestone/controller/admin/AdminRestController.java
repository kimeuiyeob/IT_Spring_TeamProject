package com.app.milestone.controller.admin;

import com.app.milestone.domain.*;
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
    @GetMapping(value= {"/userschool/{page}", "/userschool/{page}/{schoolAddress}","/userschool/{page}/{schoolAddress}/{schoolName}"})
    public SchoolResp userListSchool(@PathVariable("page") Integer page, Search search, Model model) {
        SchoolResp schoolResp = new SchoolResp();
        Pageable pageable = PageRequest.of(page, 7);
        Page<SchoolDTO> arSchoolDTO = schoolService.schoolListBudgetSearch(page, search);
        schoolResp.setArSchoolDTO(arSchoolDTO);
        schoolResp.setTotal(schoolService.schoolListCount(pageable, search));
        return schoolResp;
    }

//    전체회원에서 보육원 선택후 검색 및 조회 (asc)
    @GetMapping(value= {"/userschoolAsc/{page}", "/userschoolAsc/{page}/{schoolAddress}","/userschoolAsc/{page}/{schoolAddress}/{schoolName}"})
    public SchoolResp userListSchoolAsc(@PathVariable("page") Integer page, Search search, Model model) {
        SchoolResp schoolResp = new SchoolResp();
        Pageable pageable = PageRequest.of(page, 7);
        Page<SchoolDTO> arSchoolDTO = schoolService.schoolListBudgetSearch(page, search);
        schoolResp.setArSchoolDTO(arSchoolDTO);
        schoolResp.setTotal(schoolService.schoolListCount(pageable, search));
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













//    탈퇴회원 조회
//    @PostMapping("/withdrawal")
//    public List<WithdrawalDTO> showWithdrawalList(Pageable pageable){
//        return withdrawalService.withdrawalList(pageable);
//    }

//    탈퇴회원 조회 (오름차순)
//    @PostMapping("/withdrawalAsc")
//    public List<WithdrawalDTO> showWithdrawalListAsc(){
//        return withdrawalService.withdrawalListAsc();
//    }


}
