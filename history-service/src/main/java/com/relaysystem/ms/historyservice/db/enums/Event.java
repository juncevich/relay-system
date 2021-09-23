package com.relaysystem.ms.historyservice.db.enums;

import com.relaysystem.ms.historyservice.db.entities.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "events")
public class Event {

    @Id
    private String id;

    private EventType changeEventType;

    private Instant created;

//    private String description;
}
