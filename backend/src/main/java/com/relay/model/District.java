package com.relay.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.relay.model.places.Station;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Document
@Data
public class District extends AbstractEntity {

    /**
     * District name
     */
    private String name;

    /**
     * Stations list
     */
    @DBRef
    private List<Station> stations;

    /**
     * Stages list
     */
    @DBRef
    private List<Stage> stages;
}
