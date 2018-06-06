package com.relay.model;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "All details about the user")
@Entity
@Data
public class User extends AbstractEntity {

    /**
     * User name
     */
    @ApiModelProperty(notes = "Name should be at keast 2 characters")
    @Size(min = 2,
            message = "Name should be at least 2 characters")
    @NonNull
    private String name;
}
