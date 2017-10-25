package com.relay.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Station extends AbstractEntity {

    private String name;

    @OneToMany(mappedBy = "station")
    private List<Relay> relay;
}
