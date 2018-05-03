package com.relay.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

/**
 * Some test comment1
 */
@Data
// @MappedSuperclass
public class AbstractEntity {

    /**
     * id
     */
    @Id
    private ObjectId id;

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
