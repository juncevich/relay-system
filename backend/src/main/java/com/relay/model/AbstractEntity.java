package com.relay.model;

import java.math.BigInteger;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    /**
     * Create entity date
     */
    @CreatedDate
    private java.time.LocalDateTime created;

    /**
     * Update entity date
     */
    @LastModifiedDate
    private java.time.LocalDateTime updated;

}
