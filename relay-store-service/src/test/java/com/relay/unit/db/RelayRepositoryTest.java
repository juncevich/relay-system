package com.relay.unit.db;

import com.relay.db.entity.items.Relay;
import com.relay.db.repository.RelayRepository;
import com.relay.unit.GenericUnitTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest()
class RelayRepositoryTest extends GenericUnitTest {

    @Autowired
    private RelayRepository relayRepository;

    @Test
    void successRelaySaving() {
        Relay relay = new Relay();
        relay.setSerialNumber("12345");

        Relay savedRelay = relayRepository.save(relay);
        assertThat(savedRelay.getSerialNumber()).isEqualTo("12345");
    }
}
