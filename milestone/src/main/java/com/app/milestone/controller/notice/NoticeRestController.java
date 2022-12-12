package com.app.milestone.controller.notice;

import com.app.milestone.domain.*;
import com.app.milestone.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/noticeRest/*")
@Slf4j
public class NoticeRestController {
    private final NoticeService noticeService;

    @GetMapping(value= {"/list/{page}", "/list/{page}/{noticeTitle}"})
    public NoticeResp adminNoticeList(@PathVariable("page") Integer page, Search search, Model model) {
        NoticeResp noticeResp = new NoticeResp();
        Pageable pageable = PageRequest.of(page, 7);
        Page<NoticeDTO> arNoticeDTO = noticeService.noticeListBySearch(page, search);
        noticeResp.setArNoticeDTO(arNoticeDTO);
        noticeResp.setTotal(noticeService.noticeListCount(pageable, search));
        return noticeResp;
    }

    @GetMapping(value= {"/listAsc/{page}", "/listAsc/{page}/{noticeTitle}"})
    public NoticeResp adminNoticeListAsc(@PathVariable("page") Integer page, Search search, Model model) {
        NoticeResp noticeResp = new NoticeResp();
        Pageable pageable = PageRequest.of(page, 7);
        Page<NoticeDTO> arNoticeDTO = noticeService.noticeListBySearchAsc(page, search);
        noticeResp.setArNoticeDTO(arNoticeDTO);
        noticeResp.setTotal(noticeService.noticeListCount(pageable, search));
        return noticeResp;
    }

    @RequestMapping("/noticeDelete")
    public void deleteNotice(HttpServletRequest request){
        String [] noticeIds = request.getParameterValues("chkArray");
        for (int i = 0; i<noticeIds.length; i++){
            noticeService.deleteByNoticeId(Long.valueOf(noticeIds[i]));
        }
    }

    @GetMapping(value = {"/info/{noticeId}"})
    public NoticeDTO info(@PathVariable("noticeId") Long noticeId) {
        return noticeService.noticeInfo(noticeId);
    }

    @GetMapping(value = {"/modify"})
    public NoticeDTO noticeModify(NoticeDTO noticeDTO){
        return noticeService.modifyNotice(noticeDTO);
    };
}
