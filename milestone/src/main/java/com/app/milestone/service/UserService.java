package com.app.milestone.service;

import com.app.milestone.entity.User;
import com.app.milestone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    //    로그인
    public String login(String userEmail, String userPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Optional<User> user = userRepository.findByUserEmail(userEmail);
        log.info("db password = {}, input password = {}", user.get().getUserPassword(), userPassword);
        if (bCryptPasswordEncoder.matches(userPassword, user.get().getUserPassword())) {
            log.info("성공");
            return "Success";
        }
        log.info("실패");
        return "Failed";
    }

    //    전체 회원 (보육원 + 일반)
    public List<User> userList(Pageable pageable) {
        return userRepository.findByCreatedDate(pageable);
    }
}
