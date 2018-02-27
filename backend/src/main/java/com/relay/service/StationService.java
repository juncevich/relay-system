package com.relay.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.relay.model.places.Station;

@Component
public class StationService {

    private static final List<Station> STATIONS = new ArrayList<>();

    private static long stationCount = 4;

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

        // if (station.getId() == null)
        // station.setId(++stationCount);
        STATIONS.add(station);
        return station;
    }

    public Station findOne(long id) {

        // return STATIONS.stream().filter(station -> station.getId() ==
        // id).findFirst().orElse(null);
        return null;
    }

    public Station deleteById(long id) {

        Iterator<Station> it = STATIONS.iterator();
        // while (it.hasNext()) {
        // Station station = it.next();
        // if (station.getId() == id) {
        // it.remove();
        // return station;
        // }
        //
        // }
        return null;
    }

}
