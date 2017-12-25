package com.relaysystem.ms.historyservice.repositories;

import static org.junit.Assert.assertEquals;

import java.time.Instant;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.relaysystem.ms.historyservice.entities.ChangeEvent;
import com.relaysystem.ms.historyservice.enums.ChangeEventType;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ChangesEventReactiveRepositoryTest {

    @Autowired
    private ChangesEventReactiveRepository changesEventReactiveRepository;

    @Before
    public void setUp() throws Exception {

        ChangeEvent changeEvent =
                new ChangeEvent("beforeTest", ChangeEventType.DELETE, Instant.now());
        changesEventReactiveRepository.save(changeEvent).block();
    }

    @Test
    public void testAddToRepository() {

        Long total = changesEventReactiveRepository.count().block();
        assertEquals(Long.valueOf(1L), total);

        ChangeEvent changeEvent = new ChangeEvent(UUID.randomUUID().toString(),
                ChangeEventType.CREATE, Instant.now());
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
