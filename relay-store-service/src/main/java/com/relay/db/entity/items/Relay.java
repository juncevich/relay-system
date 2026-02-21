package com.relay.db.entity.items;

import com.relay.core.model.RelayType;
import com.relay.db.entity.storage.Shelf;
import com.relay.db.entity.storage.Storage;
import com.relay.infrastructure.db.annotation.EntityId;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.OffsetDateTime;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Relay {

    @Id
    @EntityId
    @GeneratedValue(generator = "snowFlakeId")
    private Long id;

    @Version
    private Long version;

    @ManyToOne(optional = false)
    private Storage storage;

    @ManyToOne
    private Shelf shelf;

    private int placeNumber;

    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private RelayType relayType;

    @CreatedDate
    @Column(name = "created_at",
            columnDefinition = "TIMESTAMP WITH TIME ZONE",
            updatable = false)
    private OffsetDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at",
            columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime updatedAt;

    @Column(name = "last_check_date")
    private OffsetDateTime lastCheckDate;

}
