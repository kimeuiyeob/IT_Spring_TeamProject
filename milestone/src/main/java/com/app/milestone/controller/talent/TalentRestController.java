package com.app.milestone.controller.talent;


import com.app.milestone.domain.Search;
import com.app.milestone.domain.TalentDTO;
import com.app.milestone.domain.TalentResp;
import com.app.milestone.entity.School;
import com.app.milestone.entity.Talent;
import com.app.milestone.repository.SchoolRepository;
import com.app.milestone.repository.TalentRepository;
import com.app.milestone.service.DonationService;
import com.app.milestone.service.SchoolService;
import com.app.milestone.service.TalentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/talentrest/*")
@Slf4j
public class TalentRestController {

    private final TalentService talentService;
    private final SchoolService schoolService;

    /*===============================================*/
    //재능 기부 목록
    @GetMapping(value = {"/list/{page}","/list/{page}/{talentCategory}","/list/{page}/{talentCategory}/{talentPlace}","/list/{page}/{talentCategory}/{talentPlace}/{talentTitle}"})
    public TalentResp search(@PathVariable("page") Integer page, Search search) {

        TalentResp talentResp = new TalentResp();
        Page<TalentDTO> arTalentDTO = talentService.talentList(page, search);
        talentResp.setArTalentDTO(arTalentDTO);

        return talentResp;
    }

    /*===============================================*/
    //재능기부 목록 상세페이지
    @GetMapping(value = {"/showmodal/{donationId}"})
    public List<TalentDTO> showDetail(@PathVariable("donationId") Long donationId) { //@PathVariable 이걸로 같은 이름을 찾아온다.
        return talentService.talentDetail(donationId);
    }

    /*===============================================*/
    //재능기부 글 작성시 저장
    @PostMapping("/write")
    public void saveWrite(@RequestBody TalentDTO talentDTO, HttpServletRequest request){

        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        talentDTO.setPeopleUserId(userId);
        talentService.registerTalent(talentDTO);
    }

    /*===============================================*/
    //보육원 => 신청하기 저장
    @PostMapping("/sign")
    @Transactional
    public void signWrite(@RequestBody Long donationId, HttpServletRequest request){

        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");

        TalentDTO talentDTO = talentService.findByDonationId(donationId);

        School school = schoolService.selectSchoolId(userId);
        Talent talent = talentService.selectDonation(talentDTO);

        talent.changeSchool(school);
        talentDTO.setSchoolUserId(userId);
        talentService.changeWrite(talentDTO);
    }

    /*===============================================*/
    //***마이페이지*** 재능기부 목록 글 수정
    @PostMapping("/change")
    public void changeWrite(@RequestBody TalentDTO talentDTO){
        log.info("탤런트 디티오 가져왔어요 : " + talentDTO);
        talentService.changeWriteMypage(talentDTO);
    }

    /*===============================================*/
    //***마이페이지*** 재능기부 목록 글 삭제
    @PostMapping("/delete")
    public void deleteWrite(@RequestBody TalentDTO talentDTO){
        talentService.deleteByDonationId(talentDTO.getDonationId());
    }



}