package com.app.milestone.service;

import com.app.milestone.entity.User;
import com.app.milestone.repository.UserCustomRepository;
import com.app.milestone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    //    로그인
    public User login(String userEmail, String userPassword) {
        return userRepository.findByUserEmail(userEmail)
                .filter(u -> u.getUserPassword().equals(userPassword))
                .orElse(null);
    }

    //    전체 회원 (보육원 + 일반)
    public List<User> userList(Pageable pageable) {
        return userRepository.findByCreatedDate(pageable);
    }
}
