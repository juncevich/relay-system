package com.relay.util;

import com.relay.db.entity.RelayEntity;
import com.relay.db.entity.location.Station;

import java.util.ArrayList;
import java.util.List;

public class RelayTestUtil {
    public static List<RelayEntity> createRelaysByStation(int stationCount, Station station) {
        List<RelayEntity> relays = new ArrayList<>(stationCount);
        for (int i = 0; i < stationCount; i++) {
            RelayEntity relay = RelayEntity.builder()
                    .station(station)
                    .serialNumber(String.valueOf(i))
                    .build();
            relays.add(relay);
        }
        return relays;
    }
}
