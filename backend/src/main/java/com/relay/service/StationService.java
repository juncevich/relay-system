package com.relay.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.relay.model.places.Station;

@Component
public class StationService {

    private static final List<Station> STATIONS = new ArrayList<>();


    static {
        STATIONS.add(new Station("Березит"));
        STATIONS.add(new Station("Кедровка"));
        STATIONS.add(new Station("Монетная"));
        STATIONS.add(new Station("Капалуха"));
    }

    public List<Station> findAll() {

        return STATIONS;
    }

    public Station save(Station station) {

        STATIONS.add(station);
        return station;
    }

    public Station findOne(long id) {

        return null;
    }

    public Station deleteById(long id) {

        Iterator<Station> it = STATIONS.iterator();
        return null;
    }

}
