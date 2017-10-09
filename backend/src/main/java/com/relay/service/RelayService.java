package com.relay.service;

import com.relay.model.Relay;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RelayService {

    private static List<Relay> relays = new ArrayList<>();

    private static long relayCount = 4;

    static {
        relays.add(new Relay(1L, "first"));
        relays.add(new Relay(2L, "second"));
        relays.add(new Relay(3L, "third"));
        relays.add(new Relay(4L, "fourth"));
    }

    public List<Relay> findAll() {
        return relays;
    }

    public Relay save(Relay relay) {
        if (relay.getId() == null)
            relay.setId(++relayCount);
        relays.add(relay);
        return relay;
    }

    public Relay findOne(long id) {
        return relays.stream().filter(relay -> relay.getId() == id).findFirst().orElse(new Relay(99L, "99"));
    }

}
