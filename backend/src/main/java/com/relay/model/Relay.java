package com.relay.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@ApiModel(description = "Relay details.")
public class Relay {
    private Long id;
    @NonNull
    @Size(min = 2, message = "Text should contain at least to characters")
    @ApiModelProperty(notes = "Text should contain at least to characters")
    private String text;
}
