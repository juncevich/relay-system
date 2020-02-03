package com.relay.db.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Data
public class Relay {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate creationDate;

    private LocalDateTime lastCheckDate;

    private LocalDate nextDateCheck;

    @Enumerated(EnumType.STRING)
    private RelayType type;

    private String serialNumber;

    @ManyToOne(optional = false)
    private Shelve shelve;

}
