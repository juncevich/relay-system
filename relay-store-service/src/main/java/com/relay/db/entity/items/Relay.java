package com.relay.db.entity.items;

import com.relay.db.entity.place.Container;
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

    @OneToOne
    private Container container;

    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private RelayType relayType;

    @CreatedDate
    @Column(name = "creation_date",
            columnDefinition = "TIMESTAMP WITH TIME ZONE",
            updatable = false)
    private OffsetDateTime creationDate;

    @LastModifiedDate
    @Column(name = "update_date",
            columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime updateDate;

    @Column(name = "last_check_date")
    private OffsetDateTime lastCheckDate;

}
