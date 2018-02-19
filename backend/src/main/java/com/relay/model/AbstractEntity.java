package com.relay.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

/**
 * Some test comment1
 */
@Data
@MappedSuperclass
public class AbstractEntity {

    /**
     * id
     */
    @Id
    @Column(nullable = false,
            updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Create entity date
     */
    @CreationTimestamp
    @Column(updatable = false)
    private Instant created;

    /**
     * Update entity date
     */
    @UpdateTimestamp
    @Column()
    private Instant updated;
}
