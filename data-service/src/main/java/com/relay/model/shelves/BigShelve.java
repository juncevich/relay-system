package com.relay.model.shelves;

import javax.persistence.Entity;

import com.relay.model.Relay;

import lombok.*;

@Entity
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
