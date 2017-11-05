package com.relay.model.statives;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.relay.model.AbstractEntity;
import com.relay.model.places.Station;
import com.relay.model.shelves.Shelve;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Stativ extends AbstractEntity {

    @OneToMany(targetEntity = Shelve.class)
    private List<Shelve> shelves;

    @ManyToOne
    private Station station;
}
