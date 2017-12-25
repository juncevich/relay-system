package com.relaysystem.ms.historyservice.entities;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.relaysystem.ms.historyservice.enums.ChangeType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Change {

    @Id
    private String id;

    private ChangeType changeType;

    private Instant created;
}
