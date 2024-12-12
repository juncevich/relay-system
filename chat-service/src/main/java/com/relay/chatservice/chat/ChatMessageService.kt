package com.relay.chatservice.chat

import com.relay.chatservice.chatroom.ChatRoomService
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import java.util.function.Function

@Service
@RequiredArgsConstructor
class ChatMessageService(
    private val chatMessageRepository: ChatMessageRepository,
    private val chatRoomService: ChatRoomService
) {

    fun save(chatMessage: ChatMessage): ChatMessage {
        val chatId = chatRoomService.getChatRoomId(
            chatMessage.senderId,
            chatMessage.recipientId,
            true
        ).orElseThrow()
        chatMessage.chatId = chatId
        chatMessageRepository.save<ChatMessage?>(chatMessage)
        return chatMessage
    }

    fun findChatMessages(
        senderId: String?,
        recipientId: String?
    ): MutableList<ChatMessage?> {
        val chatId = chatRoomService.getChatRoomId(
            senderId,
            recipientId,
            false
        )

        return chatId.map<MutableList<ChatMessage?>>(Function { chatId: String? ->
            chatMessageRepository.findByChatId(
                chatId
            )
        }).orElse(
            ArrayList<ChatMessage?>()
        )
    }
}
