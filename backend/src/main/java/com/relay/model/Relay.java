package com.relay.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Relay {
    private Long id;
    @NonNull
    @Size(min = 2, message = "Text should contain at least to characters")
    private String text;
}
