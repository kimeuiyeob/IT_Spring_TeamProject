package com.app.milestone.controller.admin;

import com.app.milestone.domain.*;
import com.app.milestone.entity.Withdrawal;
import com.app.milestone.service.*;
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
    private final WithdrawalService withdrawalService;
    private final MoneyService moneyService;


//    전체회원에서 일반회원 선택 후 검색 및 조회
    @GetMapping(value= {"/userpeople/{page}", "/userpeople/{page}/{keyword}"})
    public PeopleResp userListPeople(@PathVariable("page") Integer page, @PathVariable(required = false) String keyword) {
        if(keyword == null){keyword= "";}
        PeopleResp peopleResp = new PeopleResp();
        Page<PeopleDTO> arPeopleDTO = peopleService.peopleListSearch(page, keyword);
        peopleResp.setArPeopleDTO(arPeopleDTO);
        return peopleResp;
    }

//    전체회원에서 일반회원 선택 후 검색 및 조회 asc
    @GetMapping(value= {"/userpeopleAsc/{page}", "/userpeopleAsc/{page}/{keyword}"})
    public PeopleResp userListPeopleAsc(@PathVariable("page") Integer page, @PathVariable(required = false) String keyword) {
        if(keyword == null){keyword= "";}
        PeopleResp peopleResp = new PeopleResp();
        Page<PeopleDTO> arPeopleDTO = peopleService.peopleListSearchAsc(page, keyword);
        peopleResp.setArPeopleDTO(arPeopleDTO);
        return peopleResp;
    }

//    전체회원에서 보육원 선택후 검색 및 조회
    @GetMapping(value= {"/userschool/{page}", "/userschool/{page}/{keyword}"})
    public SchoolResp userListSchool(@PathVariable("page") Integer page, @PathVariable(required = false) String keyword) {
        if(keyword == null){keyword= "";}
        SchoolResp schoolResp = new SchoolResp();
        Page<SchoolDTO> arSchoolDTO = schoolService.schoolListSearch(page, keyword);
        schoolResp.setArSchoolDTO(arSchoolDTO);
        return schoolResp;
    }

//    전체회원에서 보육원 선택후 검색 및 조회 asc
    @GetMapping(value= {"/userschoolAsc/{page}", "/userschoolAsc/{page}/{keyword}"})
    public SchoolResp userListSchoolAsc(@PathVariable("page") Integer page, @PathVariable(required = false) String keyword) {
        if(keyword == null){keyword= "";}
        SchoolResp schoolResp = new SchoolResp();
        Page<SchoolDTO> arSchoolDTO = schoolService.schoolListSearchAsc(page, keyword);
        schoolResp.setArSchoolDTO(arSchoolDTO);
        return schoolResp;
    }


    //    보육원회원에서 검색 및 예산
    @GetMapping(value= {"/schoolbudget/{page}", "/schoolbudget/{page}/{keyword}"})
    public SchoolResp schoolListBudget(@PathVariable("page") Integer page, @PathVariable(required = false) String keyword) {
        if(keyword == null){keyword= "보육원";}
        SchoolResp schoolResp = new SchoolResp();
        Page<SchoolDTO> arSchoolDTO = schoolService.schoolListBudgetSearch(page, keyword);
        schoolResp.setArSchoolDTO(arSchoolDTO);
        return schoolResp;
    }

    //    보육원회원에서 검색 및 예산 asc
    @GetMapping(value= {"/schoolbudgetAsc/{page}", "/schoolbudgetAsc/{page}/{keyword}"})
    public SchoolResp schoolListBudgetAsc(@PathVariable("page") Integer page, @PathVariable(required = false) String keyword) {
        if(keyword == null){keyword= "보육원";}
        SchoolResp schoolResp = new SchoolResp();
        Page<SchoolDTO> arSchoolDTO = schoolService.schoolListBudgetSearchAsc(page, keyword);
        schoolResp.setArSchoolDTO(arSchoolDTO);
        return schoolResp;
    }

    //   전체회원 중 일반회원 삭제
    @RequestMapping("/peopleDelete")
    public void deletePeople(HttpServletRequest request){
        String [] userIds = request.getParameterValues("chkArray");
        for (int i = 0; i<userIds.length; i++){
            peopleService.deleteByUserId(Long.valueOf(userIds[i]));
        }
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

    //    탈퇴회원 조회 asc
    @GetMapping(value= {"/withdrawalAsc/{page}", "/withdrawalAsc/{page}/{withdrawalReason}"})
    public WithdrawalResp withdrawalAsc(@PathVariable("page") Integer page, Search search, Model model) {
        WithdrawalResp withdrawalResp = new WithdrawalResp();
        Pageable pageable = PageRequest.of(page, 7);
        Page<WithdrawalDTO> arWithdrawalDTO = withdrawalService.withdrawalListSearchAsc(page, search);
        withdrawalResp.setArWithdrawalDTO(arWithdrawalDTO);
        withdrawalResp.setTotal(withdrawalService.withdrawalListCount(pageable, search));
        return withdrawalResp;
    }




    //    현금기부 조회 : 최신순, 많은순
    @GetMapping(value= {"/money/{page}", "/money/{page}/{keyword}"})
    public MoneyResp moneyList(@PathVariable("page") Integer page, @PathVariable(required = false)String keyword) {
        if(keyword == null){keyword= "";}
        MoneyResp moneyResp = new MoneyResp();
        Page<MoneyDTO> arMoneyDTO = moneyService.moneyListSearch(page, keyword);
        moneyResp.setArMoneyDTO(arMoneyDTO);
        return moneyResp;
    }

    //    현금기부 조회 : 오래된순, 많은순
    @GetMapping(value= {"/moneyAsc/{page}", "/moneyAsc/{page}/{keyword}"})
    public MoneyResp moneyListAsc(@PathVariable("page") Integer page, @PathVariable(required = false)String keyword) {
        if(keyword == null){keyword= "";}
        MoneyResp moneyResp = new MoneyResp();
        Page<MoneyDTO> arMoneyDTO = moneyService.moneyListSearchAsc(page, keyword);
        moneyResp.setArMoneyDTO(arMoneyDTO);
        return moneyResp;
    }

    //    현금기부 조회 : 최신순, 적은순
    @GetMapping(value= {"/moneyAmount/{page}", "/moneyAmount/{page}/{keyword}"})
    public MoneyResp moneyAmountList(@PathVariable("page") Integer page, @PathVariable(required = false)String keyword) {
        if(keyword == null){keyword= "";}
        MoneyResp moneyResp = new MoneyResp();
        Page<MoneyDTO> arMoneyDTO = moneyService.moneyListSearchAmount(page, keyword);
        moneyResp.setArMoneyDTO(arMoneyDTO);
        return moneyResp;
    }

    //    현금기부 조회 : 오래된순, 적은순
    @GetMapping(value= {"/moneyAmountAsc/{page}", "/moneyAmountAsc/{page}/{keyword}"})
    public MoneyResp moneyAmountListAsc(@PathVariable("page") Integer page, @PathVariable(required = false)String keyword) {
        if(keyword == null){keyword= "";}
        MoneyResp moneyResp = new MoneyResp();
        Page<MoneyDTO> arMoneyDTO = moneyService.moneyListSearchAmountAsc(page, keyword);
        moneyResp.setArMoneyDTO(arMoneyDTO);
        return moneyResp;
    }

}
