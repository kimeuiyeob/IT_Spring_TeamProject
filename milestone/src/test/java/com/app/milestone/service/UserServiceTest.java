package com.app.milestone.service;

import com.app.milestone.entity.User;
import com.app.milestone.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class UserServiceTest {
    @Autowired
    private UserService userService;

//    @Test
//    public void loginTest() {
//        userService.login("cyon8254@naver.com", "phj@971204");
//    }
}
