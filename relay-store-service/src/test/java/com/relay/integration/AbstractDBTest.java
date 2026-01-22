package com.relay.integration;

import com.relay.RelaySystemApplication;
import com.relay.config.DbConfig;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { DbConfig.class, RelaySystemApplication.class })
@SpringBootTest
@AutoConfigureTestDatabase
abstract class AbstractDBTest {

}
