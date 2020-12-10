package com.relay.web.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Size;
import java.time.OffsetDateTime;

@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "Relay details.")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Relay {

    /**
     * Date of manufacture relay
     */
    @ApiModelProperty(notes = "Date of manufacture relay")
    private OffsetDateTime dateOfManufacture;

    /**
     * Relay verification date
     */
    @ApiModelProperty(notes = "Verification date relay")
    private OffsetDateTime verificationDate;

    /**
     * Relay serial number
     */
    @ApiModelProperty(notes = "Serial number relay")
    @NonNull
    @Size(min = 5,
            message = "Serial number should contain at least five characters")
    private String serialNumber;

}
