package com.relay.integration;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.relay.RelaySystemApplication;
import com.relay.config.DbConfig;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { DbConfig.class, RelaySystemApplication.class })
@SpringBootTest
@AutoConfigureTestDatabase
public abstract class AbstractDBTest extends TestCase {

}
