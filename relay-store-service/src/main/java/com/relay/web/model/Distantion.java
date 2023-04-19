package com.relay.web.model;

import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Distantion {

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
