package com.relay.model.shelves;

import javax.persistence.Entity;

import com.relay.model.Relay;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class StandartShelve extends Shelve {

    private Relay[] relays = new Relay[8];
}
