package com.relay.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
