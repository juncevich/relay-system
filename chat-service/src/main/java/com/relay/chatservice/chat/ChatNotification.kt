package com.relay.chatservice.chat

import org.springframework.data.mongodb.core.mapping.Document

@Document
data class ChatNotification(
    var id: String? = null,
    var senderId: String? = null,
    var recipientId: String? = null,
    var content: String? = null
)
