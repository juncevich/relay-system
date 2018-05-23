package com.relay.repository;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
@DirtiesContext
public abstract class AbstractMongoDBTest extends TestCase {

    /**
     * please store Starter or RuntimeConfig in a static final field
     * if you want to use artifact store caching (or else disable caching)
     */
    private  MongodStarter starter = MongodStarter.getDefaultInstance();

    private MongodExecutable _mongodExe;
    private MongodProcess _mongod;

    private MongoClient _mongo;

    @Override
    protected void setUp() throws Exception {

        _mongodExe = starter.prepare(new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net("localhost", 12345, Network.localhostIsIPv6()))
                .build());
        _mongod = _mongodExe.start();

        super.setUp();

        _mongo = new MongoClient("localhost", 12345);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        _mongod.stop();
        _mongodExe.stop();
    }

    public Mongo getMongo() {
        return _mongo;
    }

}
