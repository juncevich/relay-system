package com.relay.model;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "All details about the user")
@Entity()
@Data
public class User extends AbstractEntity {

    @Size(min = 2,
            message = "Name should be at keast 2 characters")
    @ApiModelProperty(notes = "Name should be at keast 2 characters")
    private String name;
}
