package com.relay.web.model;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
//@ApiModel(description = "Relay details.")
public class Relay {

    //    @ApiModelProperty(notes = "Date of manufacture relay")
    OffsetDateTime createdAt;
    //    @ApiModelProperty(notes = "Verification date relay")
    OffsetDateTime verificationDate;
    //    @ApiModelProperty(notes = "Serial number relay")
    @NonNull
    @Size(min = 5, message = "Serial number should contain at least five characters")
    String         serialNumber;
}
