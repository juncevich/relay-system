package com.relay.chatservice.user

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class UserService(private val userRepository: UserRepository) {

    fun saveUser(user: User) {
        user.status = Status.ONLINE
        userRepository.save<User?>(user)
    }

    fun disconnect(user: User) {
        val storedUser = userRepository.findById(user.userName).orElse(null)
        if (storedUser != null) {
            storedUser.status = Status.OFFLINE
            userRepository.save<User?>(storedUser)
        }
    }

    fun findConnectedUsers(): MutableList<User?>? {
        return userRepository.findAllByStatus(Status.ONLINE)
    }
}
