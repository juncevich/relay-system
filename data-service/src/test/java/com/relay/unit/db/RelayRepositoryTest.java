package com.relay.unit.db;

import com.relay.db.repository.RelayRepository;
import com.relay.unit.GenericUnitTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RelayRepositoryTest extends GenericUnitTest {

    @Autowired
    private RelayRepository relayRepository;

//    @Test
//    public void name() {
//        RelayEntity relay = new RelayEntity();
//        relay.setSerialNumber("12345");
//
//        relayRepository.save(relay);
//    }
}
