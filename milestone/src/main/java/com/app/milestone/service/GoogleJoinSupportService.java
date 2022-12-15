package com.app.milestone.service;

import com.app.milestone.entity.User;
import com.app.milestone.repository.UserCustomRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class GoogleJoinSupportService {

    private final UserCustomRepositoryImpl userCustomRepositoryImpl;

    public User PeopleDuplicated(String password) {
        return userCustomRepositoryImpl.OAuthIdFind(password);
    }

}
