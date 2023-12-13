package com.relay.chatservice.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void saveUser(User user) {

    }

    public void disconnect(User user) {

    }

    public List<User> findConnectedUsers() {
        return null;
    }
}
