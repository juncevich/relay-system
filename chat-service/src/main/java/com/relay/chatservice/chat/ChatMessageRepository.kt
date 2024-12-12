package com.relay.chatservice.chat

import org.springframework.data.mongodb.repository.MongoRepository

interface ChatMessageRepository : MongoRepository<ChatMessage?, String?> {
    fun findByChatId(chatId: String?): MutableList<ChatMessage?>?
}
