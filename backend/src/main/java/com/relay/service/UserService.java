package com.relay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.relay.model.User;
import com.relay.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public Flux<User> findAll() {

        return userRepository.findAll();
    }

    public Mono<User> save(User user) {

        return userRepository.save(user);
    }

    public Mono<User> findOne(long id) {

        return userRepository.findById(id);
    }

    public void deleteById(long id) {

        userRepository.deleteById(id);
    }

}
