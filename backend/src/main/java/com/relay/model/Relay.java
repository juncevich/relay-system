package com.relay.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@ApiModel(description = "Relay details.")
public class Relay {

    @Id
    @GeneratedValue
    @NonNull
    private Long id;

    @NonNull
    @Size(min = 2, message = "Text should contain at least to characters")
    @ApiModelProperty(notes = "Text should contain at least to characters")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Station station;
}
