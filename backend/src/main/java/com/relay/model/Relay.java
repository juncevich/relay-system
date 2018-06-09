package com.relay.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "Relay details.")
@Entity
@Data
@Builder
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
     * Zone date time
     */
    @JsonIgnore
    private ZonedDateTime time;

    /**
     * Date of manufacture relay
     */
    private LocalDate dateOfManufacture;

    /**
     * Relay constructor
     * 
     * @param text
     *            relay text
     */
    @Size(min = 2,
            message = "Text should contain at least to characters")
    public Relay(final String text) {

        this.text = text;
    }

}
