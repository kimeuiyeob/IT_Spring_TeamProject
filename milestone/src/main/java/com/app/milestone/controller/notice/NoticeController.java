package com.app.milestone.controller.notice;


import com.app.milestone.domain.NoticeDTO;
import com.app.milestone.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/notice/*")
public class NoticeController {
    private final NoticeService noticeService;

    @GetMapping(value = {"notice", "notice1", "notice2", "notice3", "notice4", "notice5", "notice6"})
    public void notice(){
    }

    @PostMapping(value = "write")
    public String noticeWrite(NoticeDTO noticeDTO){
        noticeService.registerNotice(noticeDTO);
        return "/admin/notice";
    };



}
