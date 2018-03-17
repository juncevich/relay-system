package com.relay.model;

import java.time.Instant;
import java.time.ZonedDateTime;

import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.relay.model.places.Station;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@ApiModel(description = "Relay details.")
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Relay {

    /**
     * id
     */
    @Id
    private String id;

    /**
     * Create entity date
     */
    @CreatedBy
    private Instant created;

    /**
     * Update entity date
     */
    @LastModifiedDate
    private Instant updated;

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
    // @ManyToOne(fetch = FetchType.LAZY)
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
    public Relay(final String id, final String someText) {

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
