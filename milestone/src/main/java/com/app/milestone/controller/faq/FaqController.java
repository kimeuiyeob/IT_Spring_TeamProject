package com.app.milestone.controller.faq;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/faq/*")
public class FaqController {

    @GetMapping(value = {"faq", "faq1", "faq2", "faq3", "faq4", "faq5", "faq6"})
    public void faq(){
    }
}
