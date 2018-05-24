package com.relay.loaders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.relay.model.Distantion;
import com.relay.model.District;
import com.relay.model.Relay;
import com.relay.model.places.Station;
import com.relay.repository.RelayRepository;

@Component
public class RelayDataLoader implements CommandLineRunner {

    private RelayRepository relayRepository;

    public RelayDataLoader(final RelayRepository relayRepository) {

        this.relayRepository = relayRepository;
    }

    @Override
    public void run(String... args) {

        if (relayRepository.count().block() == 0L) {
            populate();
        } else {
            relayRepository.deleteAll().subscribe();
            populate();
        }

    }

    private void populate() {

        Relay relay1 = new Relay("Text");
        Relay relay2 = new Relay("Text1");
        Relay relay3 = new Relay("Text2");
        Relay relay4 = new Relay("Text3");
        Relay relay5 = new Relay("Text4");

        List<Relay> relayToSave = Arrays.asList(relay1, relay2, relay3, relay4, relay5);
        relayRepository.saveAll(relayToSave).subscribe();

        Station station1 = new Station("Test station 1");
        Station station2 = new Station("Test station 2");
        District district = new District();
        district.setName("Test district");
        district.setStations(Arrays.asList(station1, station2));

        Distantion distantion = new Distantion();
        distantion.setName("Test distantion");
        distantion.setDistricts(Collections.singletonList(district));
    }
}
