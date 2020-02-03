package com.relay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.relay.web.model.User;
import com.relay.db.repository.UserRepository;

@Component
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public Iterable<User> findAll() {

        return userRepository.findAll();
    }

    public User save(User user) {

        return userRepository.save(user);
    }

    public User findOne(long id) {

        return userRepository.findById(id);
    }

    public void deleteById(long id) {

        userRepository.deleteById(id);
    }

}
