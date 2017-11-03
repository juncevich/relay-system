package com.relay.model.places;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.relay.model.Relay;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Store extends Place {

    @OneToMany(mappedBy = "station")
    private List<Relay> relay;
}
