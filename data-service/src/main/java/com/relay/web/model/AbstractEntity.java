package com.relay.web.model;

import java.time.OffsetDateTime;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

/**
 * Some test comment1
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Create entity date
     */
    @CreatedDate
    private OffsetDateTime created;

    /**
     * Update entity date
     */
    @LastModifiedDate
    private OffsetDateTime updated;

}
