package com.relay.model;

import java.util.List;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class Stativ extends AbstractEntity {

    private List<Shelve> shelves;

}
