package com.relay.db.entity.items;

import com.relay.db.entity.place.Container;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import ru.relay.infrastructure.db.annotation.EntityId;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Relay {

    @Id
    //TODO: think about how to move such annotations to infrastructure
    @GeneratedValue(generator = "snowFlakeId")
    @GenericGenerator(name = "snowFlakeId", strategy = "ru.relay.infrastructure.db.generator.SnowflakeEntityIdGenerator")
    @EntityId
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
