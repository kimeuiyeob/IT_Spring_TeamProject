package com.app.milestone.controller.join;

import com.app.milestone.domain.LoginDTO;
import com.app.milestone.domain.PeopleDTO;
import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.service.PeopleService;
import com.app.milestone.service.SchoolService;
import com.app.milestone.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login/*")
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final PeopleService peopleService;
    private final SchoolService schoolService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO) {
        log.info("userEmail = {}, userPassword = {}", loginDTO.getUserEmail(), loginDTO.getUserPassword());
        if(userService.login(loginDTO.getUserEmail(), loginDTO.getUserPassword()).equals("Success")) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
