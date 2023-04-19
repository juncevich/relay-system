package com.relay.web.model;

import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stage {

    /**
     * Stage name
     */
    private String name;

    /**
     * RailwayPoint list
     */
    @OneToMany
    private List<RailwayPoint> railwayPoints;
}
