package com.relay.web.model;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;

import lombok.*;

import javax.validation.constraints.Size;
import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@ApiModel(description = "Relay details.")
public class Relay {

    //    @ApiModelProperty(notes = "Date of manufacture relay")
    OffsetDateTime dateOfManufacture;
    //    @ApiModelProperty(notes = "Verification date relay")
    OffsetDateTime verificationDate;
    //    @ApiModelProperty(notes = "Serial number relay")
    @NonNull
    @Size(min = 5, message = "Serial number should contain at least five characters")
    String         serialNumber;
}
