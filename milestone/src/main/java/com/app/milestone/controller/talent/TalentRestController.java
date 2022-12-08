package com.app.milestone.controller.talent;


import com.app.milestone.domain.Search;
import com.app.milestone.domain.TalentDTO;
import com.app.milestone.domain.TalentResp;
import com.app.milestone.service.TalentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/talentrest/*")
@Slf4j
public class TalentRestController {

    private final TalentService talentService;

    //재능 기부 목록
    @GetMapping(value = {"/list/{page}","/list/{page}/{talentPlace}", "/list/{page}/{talentPlace}/{talentTitle}"})
    public TalentResp search(@PathVariable("page") Integer page, Search search, Model model) {
        TalentResp talentResp = new TalentResp();
        Pageable pageable = PageRequest.of(page, 10);
        Page<TalentDTO> arTalentDTO = talentService.talentList(page, search);
        talentResp.setArTalentDTO(arTalentDTO);
        return talentResp;
    }

    /*===============================================*/

    //전체 리스트 조회하기
    @PostMapping("/showall")
    public List<TalentDTO> showAll() {
        return talentService.allList();
    }//page를 받아와야한다.
    //교육 리스트 조회하기
    @PostMapping("/showeducation")
    public List<TalentDTO> showEducation() {
        return talentService.educationList();
    }
    //운동 리스트 조회하기
    @PostMapping("/showexercise")
    public List<TalentDTO> showExercise() {
        return talentService.exerciseList();
    }
    //음악 리스트 조회하기
    @PostMapping("/showmusic")
    public List<TalentDTO> showMusic() {
        return talentService.musicList();
    }
    //미술 리스트 조회하기
    @PostMapping("/showart")
    public List<TalentDTO> showArt() {
        return talentService.artList();
    }
    //It 리스트 조회하기
    @PostMapping("/showit")
    public List<TalentDTO> showIt() {
        return talentService.itList();
    }

    /*===============================================*/

    //재능기부 목록 상세페이지
    @GetMapping(value = {"/showmodal/{peopleUserId}"})
    public List<TalentDTO> showDetail(@PathVariable("peopleUserId") Long userId) { //@PathVariable 이걸로 같은 이름을 찾아온다.
        log.info("여기에 아이디값이 있어야죠?" + userId);
        log.info("레스트컨트롤러로 넘어옴");
        return talentService.talentDetail(userId);
    }

    /*===============================================*/

    //글 작성시 저장
    @PostMapping("/write")
    public void saveBoard(@RequestBody TalentDTO talentDTO){

        log.info("래스트 컨트롤러 write로 잘왔습니다!!");
        log.info("값이 있나요? 있어야됩니다!!!" + talentDTO);
        talentDTO.setPeopleUserId(101L);
        talentService.registerTalent(talentDTO);
    }


}