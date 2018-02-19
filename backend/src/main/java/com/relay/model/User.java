package com.relay.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

@ApiModel(description = "All details about the user")
@Entity
@Data
public class User {

    /**
     * id
     */
    @Id
    @Column(nullable = false,
            updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * User name
     */
    @ApiModelProperty(notes = "Name should be at keast 2 characters")
    @Size(min = 2,
            message = "Name should be at keast 2 characters")
    @NonNull
    private String name;
}
