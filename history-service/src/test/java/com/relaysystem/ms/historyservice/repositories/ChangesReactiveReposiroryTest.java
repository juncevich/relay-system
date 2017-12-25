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

import com.relaysystem.ms.historyservice.entities.Change;
import com.relaysystem.ms.historyservice.enums.ChangeType;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ChangesReactiveReposiroryTest {

    @Autowired
    private ChangesReactiveReposirory changesReactiveReposirory;

    @Before
    public void setUp() throws Exception {

        Change change = new Change("beforeTest", ChangeType.DELETE, Instant.now());
        changesReactiveReposirory.save(change).block();
    }

    @Test
    public void testAddToRepository() {

        Long total = changesReactiveReposirory.count().block();
        assertEquals(Long.valueOf(1L), total);

        Change change = new Change(UUID.randomUUID().toString(), ChangeType.CREATE, Instant.now());
        changesReactiveReposirory.save(change).block();

        total = changesReactiveReposirory.count().block();
        assertEquals(Long.valueOf(2L), total);
    }

    @Test
    public void testDeleteFromRepository() {

        Long total = changesReactiveReposirory.count().block();
        assertEquals(Long.valueOf(1), total);
        changesReactiveReposirory.deleteById("beforeTest").block();

        total = changesReactiveReposirory.count().block();
        assertEquals(Long.valueOf(0), total);
    }

}
