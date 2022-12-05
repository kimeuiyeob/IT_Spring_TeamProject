package com.app.milestone.controller.talent;


import com.app.milestone.domain.Search;
import com.app.milestone.domain.TalentDTO;
import com.app.milestone.service.TalentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/talentrest/*")
@Slf4j
public class TalentRestController {

    private final TalentService talentService;

    /*===============================================*/

    @GetMapping(value = {"/list/{talentPlace}", "/list/{talentPlace}/{talentTitle}"})
    public List<TalentDTO> search(Pageable pageable, Search search, Model model) {

        pageable = PageRequest.of(0, 10);

        List<TalentDTO> talentDTO = talentService.talentList(pageable, search);
        if (search.getTalentPlace() == null) {
            search.setTalentPlace(new ArrayList<>());
        }
        log.info("여기에 값있니?" + talentDTO);
        return talentDTO;
    }

    /*===============================================*/

    @PostMapping("/showall") //전체 리스트 조회하기
    public List<TalentDTO> showAll() {
        return talentService.allList();
    }

    @PostMapping("/showeducation") //교육 리스트 조회하기
    public List<TalentDTO> showEducation() {
        return talentService.educationList();
    }

    @PostMapping("/showexercise") //운동 리스트 조회하기
    public List<TalentDTO> showExercise() {
        return talentService.exerciseList();
    }

    @PostMapping("/showmusic") //음악 리스트 조회하기
    public List<TalentDTO> showMusic() {
        return talentService.musicList();
    }

    @PostMapping("/showart") //미술 리스트 조회하기
    public List<TalentDTO> showArt() {
        return talentService.artList();
    }

    @PostMapping("/showit") //It 리스트 조회하기
    public List<TalentDTO> showIt() {
        return talentService.itList();
    }

    @GetMapping(value = {"/showmodal/{peopleUserId}"})
    public List<TalentDTO> showDetail(@PathVariable("peopleUserId") Long userId) { //@PathVariable 이걸로 같은 이름을 찾아온다.
        log.info("여기에 아이디값이 있어야죠?" + userId);
        log.info("레스트컨트롤러로 넘어옴");
        return talentService.talentDetail(userId);
    }

//    @GetMapping("/showmodal")
//    public void showDetail() { //@PathVariable 이걸로 같은 이름을 찾아온다.
//        log.info("레스트컨트롤러로 넘어옴");
//    }

}