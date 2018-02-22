package com.relay.model.places;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.relay.model.Relay;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Document
@Data
@EqualsAndHashCode(callSuper = true)
public class Store extends Place {

    /**
     * Relay list
     */
    private List<Relay> relay;
}
