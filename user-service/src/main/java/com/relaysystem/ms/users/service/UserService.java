package com.relaysystem.ms.users.service;

import com.relaysystem.ms.users.shared.*;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDetails);
}
