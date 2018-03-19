package com.relay.loaders;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.relay.model.Relay;
import com.relay.repository.RelayRepository;

import reactor.core.publisher.Flux;

@Component
public class RelayDataLoader implements CommandLineRunner {

    private RelayRepository relayRepository;

    public RelayDataLoader(final RelayRepository relayRepository) {

        this.relayRepository = relayRepository;
    }

    @Override
    public void run(String... args) {

        if (relayRepository.count().block() == 0L) {
            Relay relay1 = new Relay("Text1");
            Relay relay2 = new Relay("Text2");
            Relay relay3 = new Relay("Text3");
            Relay relay4 = new Relay("Text4");

            Flux<Relay> relayToSave = Flux.just(relay1, relay2, relay3, relay4);
            relayToSave.toStream().forEach(i -> relayRepository.save(i).subscribe());
        } else {
            relayRepository.deleteAll().subscribe();
        }

    }
}
