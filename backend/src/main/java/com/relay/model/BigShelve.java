package com.relay.model;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class BigShelve extends Shelve {

    private Relay[] relays = new Relay[4];
}
