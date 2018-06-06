package com.relay.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Distantion extends AbstractEntity {

    /**
     * Distantion name
     */
    private String name;

    /**
     * List of the districts
     */
    @OneToMany
    private List<District> districts;
}
