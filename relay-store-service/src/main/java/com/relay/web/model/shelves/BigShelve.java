package com.relay.web.model.shelves;

import com.relay.web.model.Relay;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
