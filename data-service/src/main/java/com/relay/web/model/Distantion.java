package com.relay.web.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
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
