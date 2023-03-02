package com.relay.integration;

import com.relay.RelaySystemApplication;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = RelaySystemApplication.class)
abstract class AbstractTestWithSpringContext {
}
