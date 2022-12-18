package com.app.milestone.controller.admin;

import com.app.milestone.domain.*;
import com.app.milestone.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/adminRest/*")
public class AdminRestController {
    private final SchoolService schoolService;
    private final PeopleService peopleService;
    private final WithdrawalService withdrawalService;
    private final MoneyService moneyService;
    private final ServiceService serviceService;
    private final TalentService talentService;


//    전체회원에서 일반회원 선택 후 검색 및 조회
    @GetMapping(value= {"/userpeople/{page}", "/userpeople/{page}/{keyword}"})
    public PeopleResp userListPeople(@PathVariable("page") Integer page, @PathVariable(required = false) String keyword) {
        if(keyword == null){keyword= "";}
        // 검색어가 들어가지 않았을 때(변수를 넣어주지않으면) 오류가 나므로
        // 파라미터 속성을 required=false로 변경후, 기본값("")을 설정해줍니다.

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
        if(keyword == null){keyword= "";}
        SchoolResp schoolResp = new SchoolResp();
        Page<SchoolDTO> arSchoolDTO = schoolService.schoolListBudgetSearch(page, keyword);
        schoolResp.setArSchoolDTO(arSchoolDTO);
        return schoolResp;
    }

    //    보육원회원에서 검색 및 예산 asc
    @GetMapping(value= {"/schoolbudgetAsc/{page}", "/schoolbudgetAsc/{page}/{keyword}"})
    public SchoolResp schoolListBudgetAsc(@PathVariable("page") Integer page, @PathVariable(required = false) String keyword) {
        if(keyword == null){keyword= "";}
        SchoolResp schoolResp = new SchoolResp();
        Page<SchoolDTO> arSchoolDTO = schoolService.schoolListBudgetSearchAsc(page, keyword);
        schoolResp.setArSchoolDTO(arSchoolDTO);
        return schoolResp;
    }

    //   전체회원 중 일반회원 삭제
    @Transactional
    @RequestMapping("/peopleDelete")
    public void deletePeople(HttpServletRequest request){
        String [] userIds = request.getParameterValues("chkArray");
        for (int i = 0; i<userIds.length; i++){
            withdrawalService.deleteEverything(Long.valueOf(userIds[i]));   //회원삭제시 연관관계가 있는 것들을 모두 찾아 삭제해준 후
            peopleService.deleteByUserId(Long.valueOf(userIds[i]));         //회원을 삭제하였습니다.
        }
    }

    //   보육원회원 삭제
    @Transactional
    @RequestMapping("/schoolDelete")
    public void deleteSchool(HttpServletRequest request){
        String [] userIds = request.getParameterValues("chkArray");
        for (int i = 0; i<userIds.length; i++){
            withdrawalService.deleteEverything(Long.valueOf(userIds[i]));
            schoolService.deleteByUserId(Long.valueOf(userIds[i]));
        }
    }

    //    탈퇴회원 조회
    @GetMapping(value= {"/withdrawal/{page}", "/withdrawal/{page}/{withdrawalReason}"})
    public WithdrawalResp withdrawalList(@PathVariable("page") Integer page, @PathVariable(required = false) String withdrawalReason) {
        if(withdrawalReason == null){withdrawalReason= "";}
        WithdrawalResp withdrawalResp = new WithdrawalResp();
        Page<WithdrawalDTO> arWithdrawalDTO = withdrawalService.withdrawalListSearch(page, withdrawalReason);
        withdrawalResp.setArWithdrawalDTO(arWithdrawalDTO);
        return withdrawalResp;
    }

    //    탈퇴회원 조회 asc
    @GetMapping(value= {"/withdrawalAsc/{page}", "/withdrawalAsc/{page}/{withdrawalReason}"})
    public WithdrawalResp withdrawalListAsc(@PathVariable("page") Integer page, @PathVariable(required = false) String withdrawalReason) {
        if(withdrawalReason == null){withdrawalReason= "";}
        WithdrawalResp withdrawalResp = new WithdrawalResp();
        Page<WithdrawalDTO> arWithdrawalDTO = withdrawalService.withdrawalListSearchAsc(page, withdrawalReason);
        withdrawalResp.setArWithdrawalDTO(arWithdrawalDTO);
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

    //    봉사 조회
    @GetMapping(value= {"/service/{page}", "/service/{page}/{keyword}"})
    public ServiceResp serviceList(@PathVariable("page") Integer page, @PathVariable(required = false)String keyword) {
        if(keyword == null){keyword= "";}
        ServiceResp serviceResp = new ServiceResp();
        Page<ServiceDTO> arServiceDTO = serviceService.serviceListSearch(page, keyword);
        serviceResp.setArServiceDTO(arServiceDTO);
        return serviceResp;
    }

    //    봉사 조회 asc
    @GetMapping(value= {"/serviceAsc/{page}", "/serviceAsc/{page}/{keyword}"})
    public ServiceResp serviceListAsc(@PathVariable("page") Integer page, @PathVariable(required = false)String keyword) {
        if(keyword == null){keyword= "";}
        ServiceResp serviceResp = new ServiceResp();
        Page<ServiceDTO> arServiceDTO = serviceService.serviceListSearchAsc(page, keyword);
        serviceResp.setArServiceDTO(arServiceDTO);
        return serviceResp;
    }

    //    재능기부 조회
    @GetMapping(value = {"/talent/{page}", "/talent/{page}/{keyword}", "/talent/{page}/{keyword}/{talentCategory}"})
    public TalentResp talentList(@PathVariable("page") Integer page, Search search) {
        TalentResp talentResp = new TalentResp();
        Page<TalentDTO> arTalentDTO = talentService.searchedTalentList(page, search);
        talentResp.setArTalentDTO(arTalentDTO);
        return talentResp;
    }

    //     재능기부 삭제
    @Transactional
    @RequestMapping("/talentDelete")
    public void deleteSchedule(HttpServletRequest request){
        String [] donationIds = request.getParameterValues("chkArray");
        for (int i = 0; i<donationIds.length; i++){
            talentService.deleteByDonationId(Long.valueOf(donationIds[i]));
        }
    }
}
