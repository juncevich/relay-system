package com.relay.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.relay.model.Relay;

@Component public class UserService {

    private static final List<Relay> relays = new ArrayList<>();

    private static long relayCount = 4;

    static {
        relays.add(new Relay("first"));
        relays.add(new Relay("second"));
        relays.add(new Relay("third"));
        relays.add(new Relay("fourth"));
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

        return relays.stream().filter(relay -> relay.getId() == id).findFirst().orElse(null);
    }

    public Relay deleteById(long id) {

        Iterator<Relay> it = relays.iterator();
        while (it.hasNext()) {
            Relay relay = it.next();
            if (relay.getId() == id) {
                it.remove();
                return relay;
            }

        }
        return null;
    }

}
