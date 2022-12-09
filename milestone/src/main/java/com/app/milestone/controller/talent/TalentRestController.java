package com.app.milestone.controller.talent;


import com.app.milestone.domain.Search;
import com.app.milestone.domain.TalentDTO;
import com.app.milestone.domain.TalentResp;
import com.app.milestone.service.TalentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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

        log.info("list 레스트 컨트롤러 페이지 : " + page);
        log.info("list 레스트 컨트롤러 설치 : " + search);

        TalentResp talentResp = new TalentResp();
        Page<TalentDTO> arTalentDTO = talentService.talentList(page, search);
        talentResp.setArTalentDTO(arTalentDTO);

        log.info("list 레스트 컨트롤러 화면들고갈 값 : " + arTalentDTO.toString());
        log.info("list 레스트 컨트롤러 화면들고갈 값 : " + talentResp.toString());

        return talentResp;
    }

    /*===============================================*/

    //재능기부 목록 상세페이지
    @GetMapping(value = {"/showmodal/{donationId}"})
    public List<TalentDTO> showDetail(@PathVariable("donationId") Long donationId) { //@PathVariable 이걸로 같은 이름을 찾아온다.
        return talentService.talentDetail(donationId);
    }

    /*===============================================*/

    //글 작성시 저장
    @PostMapping("/write")
    public void saveBoard(@RequestBody TalentDTO talentDTO){
        talentDTO.setPeopleUserId(101L); // <=======================================세션값으로 변경
        talentService.registerTalent(talentDTO);
    }


}