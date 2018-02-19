package com.relay.model.shelves;

import org.springframework.data.mongodb.core.mapping.Document;

import com.relay.model.Relay;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Document
@Data
@EqualsAndHashCode(callSuper = true)
public class BigShelve extends Shelve {

    /**
     * Relays array
     */
    private Relay[] relays = new Relay[4];
}
