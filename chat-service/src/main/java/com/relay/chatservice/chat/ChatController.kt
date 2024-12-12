package com.relay.chatservice.chat

import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
@RequiredArgsConstructor
class ChatController(
    private val chatMessageService: ChatMessageService,
    private val messagingTemplate: SimpMessagingTemplate
) {

    @MessageMapping("/chat")
    fun processMessage(
        @Payload chatMessage: ChatMessage
    ) {
        val savedMsg = chatMessageService.save(chatMessage)

        // john/queue/message
        messagingTemplate.convertAndSendToUser(
            chatMessage.recipientId!!,
            "/queue/messages",
            ChatNotification(
                savedMsg.id,
                savedMsg.senderId,
                savedMsg.recipientId,
                savedMsg.content
            )
        )
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    fun findChatMessages(
        @PathVariable("senderId") senderId: String?,
        @PathVariable("recipientId") recipientId: String?
    ): ResponseEntity<MutableList<ChatMessage?>?> {
        return ResponseEntity.ok<MutableList<ChatMessage?>?>(
            chatMessageService.findChatMessages(
                senderId,
                recipientId
            )
        )
    }
}
