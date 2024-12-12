package com.relay.chatservice.chat;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class ChatNotification {
    private String id;
    private String senderId;
    private String recipientId;
    private String content;
}
