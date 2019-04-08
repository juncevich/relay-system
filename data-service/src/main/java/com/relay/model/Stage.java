package com.relay.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stage extends AbstractEntity {

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
