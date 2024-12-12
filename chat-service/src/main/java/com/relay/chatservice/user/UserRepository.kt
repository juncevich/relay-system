package com.relay.chatservice.user

import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User?, String?> {
    fun findAllByStatus(status: Status?): MutableList<User?>?
}
