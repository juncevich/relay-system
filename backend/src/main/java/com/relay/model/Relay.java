package com.relay.model;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.relay.model.places.Station;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@ApiModel(description = "Relay details.")
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Relay extends AbstractEntity {

    @ApiModelProperty(notes = "Text should contain at least to characters")
    @NonNull
    @Size(min = 2,
            message = "Text should contain at least to characters")
    private String text;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Station station;

    private ZonedDateTime time;

    public Relay(long id, String text) {

        this.setId(id);
        this.setText(text);
    }

}
