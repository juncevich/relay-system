package com.relay.model.places;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.relay.model.Relay;
import com.relay.model.statives.Stativ;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Station extends Place {

    private String name;

    @OneToMany(targetEntity = Relay.class)
    private List<Relay> relay;

    @OneToMany(targetEntity = Stativ.class)
    private List<Stativ> statives;
}
