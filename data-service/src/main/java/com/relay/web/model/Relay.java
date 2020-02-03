package com.relay.web.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "Relay details.")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Relay extends AbstractEntity {

    /**
     * Date of manufacture relay
     */
    @ApiModelProperty(notes = "Date of manufacture relay")
    private LocalDate dateOfManufacture;

    /**
     * Relay verification date
     */
    @ApiModelProperty(notes = "Verification date relay")
    private LocalDate verificationDate;

    /**
     * Relay serial number
     */
    @ApiModelProperty(notes = "Serial number relay")
    @NonNull
    @Size(min = 5,
            message = "Serial number should contain at least five characters")
    private String serialNumber;

}
