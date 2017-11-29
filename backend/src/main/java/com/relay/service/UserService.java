package com.relay.service;

import com.relay.model.User;
import com.relay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {

        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findOne(long id) {
        return userRepository.findById(id);
    }

    public User deleteById(long id) {
        return userRepository.deleteById(id);
    }

}
