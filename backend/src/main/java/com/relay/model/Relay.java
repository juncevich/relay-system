package com.relay.model;

import java.time.ZonedDateTime;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.relay.model.places.Station;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@ApiModel(description = "Relay details.")
@Document
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Relay extends AbstractEntity {

    /**
     * Relay text
     */
    @ApiModelProperty(notes = "Text should contain at least to characters")
    @NonNull
    @Size(min = 2,
            message = "Text should contain at least to characters")
    private String text;

    /**
     * Station
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Station station;

    /**
     * Zone date time
     */
    private ZonedDateTime time;

    /**
     * Relay constructor
     * 
     * @param id
     *            relay id
     * @param someText
     *            relay text
     */
    public Relay(final long id, final String someText) {

        this.setId(id);
        this.setText(someText);
    }

    /**
     * Relay constructor
     * 
     * @param text
     *            relay text
     */
    public Relay(@Size(min = 2,
            message = "Text should contain at least to characters") final String text) {

        this.text = text;
    }

}
