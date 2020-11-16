package com.relay.unit.db;

import com.relay.db.entity.RelayEntity;
import com.relay.db.repository.RelayRepository;
import com.relay.unit.GenericUnitTest;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@Ignore
@RunWith(SpringRunner.class)
@DataJpaTest
public class RelayRepositoryTest extends GenericUnitTest {

    @Autowired
    private RelayRepository relayRepository;

    @Test
    public void name() {
        RelayEntity relay = new RelayEntity();
        relay.setSerialNumber("12345");

        relayRepository.save(relay);
    }
}
