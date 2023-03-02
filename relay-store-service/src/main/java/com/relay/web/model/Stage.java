package com.relay.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
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
