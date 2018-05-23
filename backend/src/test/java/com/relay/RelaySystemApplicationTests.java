package com.relay;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.relay.config.ReactiveMongoConfig;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { RelaySystemApplication.class})
public class RelaySystemApplicationTests {

    @Test
    public void contextLoads() {

        // Empty method
    }

}
