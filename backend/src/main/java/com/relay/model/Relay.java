package com.relay.model;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@ApiModel(description = "Relay details.")
public class Relay extends AbstractEntity {

    @NonNull
    @Size(min = 2,
            message = "Text should contain at least to characters")
    @ApiModelProperty(notes = "Text should contain at least to characters")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Station station;

    private ZonedDateTime time;

    public Relay(long id, String text) {

        this.setId(id);
        this.setText(text);
    }

}
