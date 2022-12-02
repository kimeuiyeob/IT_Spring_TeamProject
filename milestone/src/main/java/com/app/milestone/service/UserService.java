package com.app.milestone.service;

import com.app.milestone.entity.User;
import com.app.milestone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

//    전체 회원 (보육원 + 일반)
    public List<User> userList(Pageable pageable){
        return userRepository.findByCreatedDate(pageable);
    }
}
