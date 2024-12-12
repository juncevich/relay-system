package com.relay.chatservice.chatroom

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class ChatRoom(
    @Id
    var id: String? = null,
    var chatId: String? = null,
    var senderId: String? = null,
    var recipientId: String? = null
)
