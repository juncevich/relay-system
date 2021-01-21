package com.relay.web.model;

import com.relay.web.model.places.Station;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class District {

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
