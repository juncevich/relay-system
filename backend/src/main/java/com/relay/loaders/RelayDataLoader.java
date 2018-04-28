package com.relay.loaders;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.relay.model.Relay;
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
    }
}
