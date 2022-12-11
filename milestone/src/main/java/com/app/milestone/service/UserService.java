package com.app.milestone.service;

import com.app.milestone.domain.LoginDTO;
import com.app.milestone.entity.User;
import com.app.milestone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final LoginDTO loginDTO;

    //    로그인
    public User login(String userEmail, String userPassword) {
        byte[] userPasswordBytes = userPassword.getBytes();
        userPasswordBytes = Base64.getEncoder().encodeToString(userPasswordBytes).getBytes();
        byte[] finalUserPasswordBytes = userPasswordBytes;
        return userRepository.findByUserEmail(userEmail)
                .filter(u -> Objects.equals(u.getUserPassword(), new String(finalUserPasswordBytes)))
                .orElse(null);
    }

    //    전체 회원 (보육원 + 일반)
    public List<User> userList(Pageable pageable) {
        return userRepository.findByCreatedDate(pageable);
    }
}
