package com.relay.web.model.places;

import com.relay.web.model.Relay;
import com.relay.web.model.statives.Stativ;
import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
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

    @OneToMany(fetch = FetchType.LAZY)
    private List<Relay> relay;

    /**
     * List of statives on the station
     */
    @OneToMany(fetch = FetchType.LAZY)
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
