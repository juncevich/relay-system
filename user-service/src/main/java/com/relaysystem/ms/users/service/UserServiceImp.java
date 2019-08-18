package com.relaysystem.ms.users.service;

import com.relaysystem.ms.users.data.*;
import com.relaysystem.ms.users.shared.*;
import org.modelmapper.*;
import org.modelmapper.convention.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class UserServiceImp implements UserService {

    UsersRepository usersRepository;

    public UserServiceImp(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDto createUser(UserDto userDetails) {
        userDetails.setUserId(UUID.randomUUID().toString());
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity entity = mapper.map(userDetails, UserEntity.class);
        entity.setEncryptedPassword("test");
        usersRepository.save(entity);
        return mapper.map(entity, UserDto.class);
    }
}
