package com.relay.db.entity.history;

import com.relay.db.entity.items.Relay;
import com.relay.db.entity.storage.Storage;
import com.relay.infrastructure.db.annotation.EntityId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RelayMovement {

    @Id
    @EntityId
    @GeneratedValue(generator = "snowFlakeId")
    private Long id;

    @ManyToOne(optional = false)
    private Relay relay;

    @ManyToOne(optional = false)
    private Storage fromStorage;

    @ManyToOne(optional = false)
    private Storage toStorage;

    private OffsetDateTime movedAt;
}