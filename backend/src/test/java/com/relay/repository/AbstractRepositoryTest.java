package com.relay.repository;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.relay.model.Relay;

@RunWith(SpringRunner.class)
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
public abstract class AbstractRepositoryTest {

    @Autowired
    private RelayRepository relayRepository;

    @Before
    public void setUp() {

        Long result = relayRepository.count().block();
        if (result == 0L) {
            populate();
        } else {
            relayRepository.deleteAll().subscribe();
            populate();
        }
    }

    private void populate() {

        Relay relay1 = new Relay("Text1");
        Relay relay2 = new Relay("Text2");
        Relay relay3 = new Relay("Text3");
        Relay relay4 = new Relay("Text4");

        List<Relay> relayToSave = Arrays.asList(relay1, relay2, relay3, relay4);
        relayRepository.saveAll(relayToSave).subscribe();
    }
}
