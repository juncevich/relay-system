package com.relay.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Document
@Data
public class Distantion extends AbstractEntity {

    /**
     * Distantion name
     */
    private String name;

    /**
     * List of the districts
     */
    @DBRef
    private List<District> districts;
}
