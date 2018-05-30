package com.relay.model;

import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NonNull;

// @Entity
@Data
public class User {

    /**
     * id
     */
    // @Id
    // @Column(nullable = false,
    // updatable = false)
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * User name
     */
    @Size(min = 2,
            message = "Name should be at keast 2 characters")
    @NonNull
    private String name;
}
