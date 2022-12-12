package com.app.milestone.controller.talent;


import com.app.milestone.domain.Search;
import com.app.milestone.domain.TalentDTO;
import com.app.milestone.domain.TalentResp;
import com.app.milestone.service.TalentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/talentrest/*")
@Slf4j
public class TalentRestController {

    private final TalentService talentService;
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
    //쟈능기부 글 작성시 저장
    @PostMapping("/write")
    public void saveWrite(@RequestBody TalentDTO talentDTO){
        talentDTO.setPeopleUserId(101L); // <=======================================세션값으로 변경
        talentService.registerTalent(talentDTO);
    }
    /*===============================================*/
    //***마이페이지*** 재능기부 목록 글 수정
    @PostMapping("/change")
    public void changeWrite(@RequestBody TalentDTO talentDTO){
        talentService.changeWrite(talentDTO);
    }
    /*===============================================*/
    //***마이페이지*** 재능기부 목록 글 삭제
    @PostMapping("/delete")
    public void deleteWrite(@RequestBody TalentDTO talentDTO){
        talentService.deleteByDonationId(talentDTO.getDonationId());
    }

}