package com.relay.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Document
@Data
public class Stage extends AbstractEntity {

    /**
     * Stage name
     */
    private String name;

    /**
     * RailwayPoint list
     */
    @DBRef
    private List<RailwayPoint> railwayPoints;
}
