package com.relay.web.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.OffsetDateTime;

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
    private BigInteger id;

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
