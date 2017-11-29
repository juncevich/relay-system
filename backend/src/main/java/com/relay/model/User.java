package com.relay.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Size;

@ApiModel(description = "All details about the user")
@Entity
@Data

public class User {

    @Id
    @Column(nullable = false,
            updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(notes = "Name should be at keast 2 characters")
    @Size(min = 2,
            message = "Name should be at keast 2 characters")
    @NonNull
    private String name;
}
