package com.relay.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@ApiModel(description = "All details about the user")
@Entity()
@Data
public class User {

    @Id
    @GeneratedValue
    private Integer id;


    @Size(min = 2, message = "Name should be at keast 2 characters")
    @ApiModelProperty(notes = "Name should be at keast 2 characters")
    private String name;
}
