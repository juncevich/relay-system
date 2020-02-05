package com.relay.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne
    private Shelve shelve;

}
