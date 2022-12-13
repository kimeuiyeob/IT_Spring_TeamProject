package com.app.milestone.service;

import com.app.milestone.domain.LoginDTO;
import com.app.milestone.entity.User;
import com.app.milestone.repository.PeopleRepository;
import com.app.milestone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PeopleRepository peopleRepository;
    private final LoginDTO loginDTO;
    
    //    로그인
    public Long login(LoginDTO loginDTO) {
        String encodingUserPassword = Base64.getEncoder().encodeToString(loginDTO.getUserPassword().getBytes());
        return userRepository.findByUserEmailAndUserPassword(loginDTO.getUserEmail(), encodingUserPassword);
    }

    //    전체 회원 (보육원 + 일반)
    public List<User> userList(Pageable pageable) {
        return userRepository.findByCreatedDate(pageable);
    }

    /*황지수*/
    public Long checkEmail(String userEmail){
        return userRepository.findByUserEmail(userEmail).get().getUserId();
    }
    /*/황지수*/
}
