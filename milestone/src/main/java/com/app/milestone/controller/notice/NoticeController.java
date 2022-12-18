package com.app.milestone.controller.notice;


import com.app.milestone.domain.NoticeDTO;
import com.app.milestone.domain.QNoticeDTO;
import com.app.milestone.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/notice/*")
public class NoticeController {
    private final NoticeService noticeService;

    @GetMapping("notice")
    public void notice() {
    ;
    }

    //  공지사항 목록페이지
    @GetMapping("/noticeList")
    public void noticeList(Long noticeId, Model model){
        model.addAttribute("notice",  noticeService.noticeInfo(noticeId));
        model.addAttribute("noticeAll" ,noticeService.selectNoticeAll());
    }

    // 공지사항 작성 : 페이지 이동
    @PostMapping(value = "write")
    public String noticeWrite(NoticeDTO noticeDTO) {
        noticeService.registerNotice(noticeDTO);
        return "/admin/notice";
    }
    ;
}
