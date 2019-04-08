package com.relay.model.shelves;

import javax.persistence.Entity;

import com.relay.model.Relay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StandartShelve extends Shelve {

    /**
     * Relays array
     */
    private Relay[] relays = new Relay[8];
}
