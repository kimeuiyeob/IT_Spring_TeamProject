package com.app.milestone.controller.notice;


import com.app.milestone.domain.NoticeDTO;
import com.app.milestone.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notice/*")
public class NoticeController {
    private final NoticeService noticeService;

    @GetMapping(value = {"notice", "notice1", "notice2", "notice3", "notice4", "notice5", "notice6"})
    public void notice(){
    }

    @PostMapping("/write")
    public void noticeWrite(NoticeDTO noticeDTO){
        noticeService.registerNotice(noticeDTO);
    };


}
