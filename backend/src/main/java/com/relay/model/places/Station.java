package com.relay.model.places;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.relay.model.Relay;
import com.relay.model.statives.Stativ;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Document
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Station extends Place {

    /**
     * Station name
     */
    @NonNull
    private String name;

    /**
     * Relay on the station
     */
    // @OneToMany(targetEntity = Relay.class)
    private List<Relay> relay;

    /**
     * List of statives on the station
     */
    // @OneToMany(targetEntity = Stativ.class)
    private List<Stativ> statives;

    /**
     * Station constructor
     * 
     * @param name
     *            station name
     */
    public Station(String name) {

        this.name = name;
    }
}
