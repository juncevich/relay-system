package com.relay.chatservice.chat

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*


@Document
data class ChatMessage(
    @Id
    var id: String? = null,
    var chatId: String? = null,
    var senderId: String? = null,
    var recipientId: String? = null,
    var content: String? = null,
    var timestamp: Date? = null
)
