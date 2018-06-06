package com.relay.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.relay.model.places.Station;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class District extends AbstractEntity {

    /**
     * District name
     */
    private String name;

    /**
     * Stations list
     */
    @OneToMany
    private List<Station> stations;

    /**
     * Stages list
     */
    @OneToMany
    private List<Stage> stages;
}
