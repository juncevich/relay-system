package com.relay.model.shelves;

import javax.persistence.Entity;

import com.relay.model.Relay;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class BigShelve extends Shelve {

    /**
     * Relays array
     */
    private Relay[] relays = new Relay[4];
}
