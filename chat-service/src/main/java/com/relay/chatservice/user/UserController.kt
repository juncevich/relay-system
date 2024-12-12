package com.relay.chatservice.user

import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping

@Service
@RequiredArgsConstructor
class UserController(private val userService: UserService) {

    @MessageMapping("/user.addUser")
    @SendTo("/user/topic")
    fun addUser(
        @Payload user: User
    ): User {
        userService.saveUser(user)
        return user
    }

    @MessageMapping("/user.disconnect")
    @SendTo("/user/topic")
    fun disconnect(
        @Payload user: User
    ): User {
        userService.disconnect(user)
        return user
    }

    @GetMapping("/users")
    fun findConnectedUsers(): ResponseEntity<MutableList<User?>?> {
        return ResponseEntity.ok<MutableList<User?>?>(userService.findConnectedUsers())
    }
}
