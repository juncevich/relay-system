package com.relay.model.statives;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.relay.model.AbstractEntity;
import com.relay.model.places.Station;
import com.relay.model.shelves.Shelve;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Document
@Data
@EqualsAndHashCode(callSuper = true)
public class Stativ extends AbstractEntity {

    /**
     * List of shelves
     */
    private List<Shelve> shelves;

    /**
     * Station on that stativ located
     */
    private Station station;
}
