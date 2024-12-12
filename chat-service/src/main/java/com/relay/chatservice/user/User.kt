package com.relay.chatservice.user

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class User(
    @Id
    val userName: String,
    val fullName: String,
    var status: Status
)
