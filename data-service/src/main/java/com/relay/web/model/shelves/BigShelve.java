package com.relay.web.model.shelves;

import javax.persistence.Entity;

import com.relay.web.model.Relay;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class BigShelve extends Shelve {

    /**
     * Relays array
     */
    private Relay[] relays = new Relay[4];
}
