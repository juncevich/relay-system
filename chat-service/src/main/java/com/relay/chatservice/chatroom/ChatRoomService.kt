package com.relay.chatservice.chatroom

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Function
import java.util.function.Supplier

@Service
@RequiredArgsConstructor
class ChatRoomService(private val chatRoomRepository: ChatRoomRepository) {


    fun getChatRoomId(
        senderId: String?,
        recipientId: String?,
        createNewRoomIfNotExists: Boolean
    ): Optional<String?> {
        return chatRoomRepository.findBySenderIdAndRecipientId(senderId, recipientId)
            .map<String?>(Function { obj: ChatRoom? -> obj!!.chatId })
            .or(
                Supplier {
                    if (createNewRoomIfNotExists) {
                        val chatId = createChatId(senderId, recipientId)
                        return@Supplier Optional.of<String?>(chatId)
                    }
                    Optional.empty<String?>()
                }
            )
    }

    private fun createChatId(senderId: String?, recipientId: String?): String {
        val chatId = "${senderId}_${recipientId}%"

        val senderRecipient = ChatRoom(
            chatId,
            senderId,
            recipientId
        )

        val recipientSender = ChatRoom(
            chatId,

            recipientId,

            senderId,

            )

        chatRoomRepository.save<ChatRoom?>(senderRecipient)
        chatRoomRepository.save<ChatRoom?>(recipientSender)

        return chatId
    }
}
