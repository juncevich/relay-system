package com.relaysystem.ms.historyservice.db.repositories;

import static org.junit.Assert.assertEquals;

import java.time.Instant;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.relaysystem.ms.historyservice.db.enums.Event;
import com.relaysystem.ms.historyservice.db.entities.EventType;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ChangesEventReactiveRepositoryTest {

    @Autowired
    private ChangesEventReactiveRepository changesEventReactiveRepository;

    @Before
    public void setUp() throws Exception {

        Event changeEvent =
                new Event("beforeTest", EventType.DELETE, Instant.now());
        changesEventReactiveRepository.save(changeEvent).block();
    }

    @Test
    public void testAddToRepository() {

        Long total = changesEventReactiveRepository.count().block();
        assertEquals(Long.valueOf(1L), total);

        Event changeEvent = new Event(UUID.randomUUID().toString(),
                EventType.CREATE, Instant.now());
        changesEventReactiveRepository.save(changeEvent).block();

        total = changesEventReactiveRepository.count().block();
        assertEquals(Long.valueOf(2L), total);
    }

    @Test
    public void testDeleteFromRepository() {

        Long total = changesEventReactiveRepository.count().block();
        assertEquals(Long.valueOf(1), total);
        changesEventReactiveRepository.deleteById("beforeTest").block();

        total = changesEventReactiveRepository.count().block();
        assertEquals(Long.valueOf(0), total);
    }

}
