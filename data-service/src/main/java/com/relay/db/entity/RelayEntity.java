package com.relay.db.entity;

import com.relay.db.entity.location.Station;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelayEntity {


    private OffsetDateTime updated;
    private OffsetDateTime created;

    @Id
    @GeneratedValue
    private Long id;

    @PrePersist
    private void prePersist() {
        OffsetDateTime createdDate = this.getCreated();
        if (createdDate == null) {
            this.setCreated(OffsetDateTime.now());
        }
    }

    @PreUpdate
    private void preUpdate() {
        this.setUpdated(OffsetDateTime.now());
    }

    @Column(name = "creation_date")
    private LocalDate creationDate;

    private LocalDateTime lastCheckDate;

    private LocalDate nextDateCheck;

    @Enumerated(EnumType.STRING)
    private RelayType type;

    private String serialNumber;

    @ManyToOne
    private Shelve shelve;

    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;

}
