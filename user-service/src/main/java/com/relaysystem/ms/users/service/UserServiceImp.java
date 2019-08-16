package com.relaysystem.ms.users.service;

import com.relaysystem.ms.users.shared.*;

import java.util.*;

public class UserServiceImp implements UserService {
    @Override
    public UserDto createUser(UserDto userDetails) {
        userDetails.setUserId(UUID.randomUUID().toString());
        return null;
    }
}
