package com.relay.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;

@Data
@MappedSuperclass
public class AbstractEntity {

    @Id
    @Column(nullable = false,
            updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant created;

    @UpdateTimestamp
    @Column()
    private Instant updated;
}
