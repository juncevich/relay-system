package com.relay.perf.testing.simulation

import com.relay.perf.testing.protocol.HttpBuilder
import io.gatling.javaapi.core.CoreDsl.constantConcurrentUsers
import io.gatling.javaapi.core.CoreDsl.rampConcurrentUsers
import io.gatling.javaapi.core.CoreDsl.scenario
import io.gatling.javaapi.core.Simulation
import io.gatling.javaapi.http.HttpDsl.http
import io.gatling.javaapi.http.HttpProtocolBuilder
import java.time.Duration

class GetRelaySimulation : Simulation() {

    private val httpProtocol: HttpProtocolBuilder = HttpBuilder().build()
    private val simulationDuration = Duration.ofMinutes(1).toSeconds()
    private val concurrentUsers = 3000;
    val scn = scenario("BasicSimulation")
        .exec(
            http("get_all_relay")
                .get("/relays")
        )

    init {
        setUp(
//            scn.injectOpen(constantUsersPerSec(1000.0).during(simulationDuration))
            scn.injectClosed(
                rampConcurrentUsers(0).to(concurrentUsers)
                    .during(simulationDuration / 2),
                constantConcurrentUsers(concurrentUsers)
                    .during(simulationDuration)
//                    constantConcurrentUsers(concurrentUsers)
//                        .during(simulationDuration)
            ).disablePauses()
//            scn.injectOpen(atOnceUsers(10000))
//        scn.inject(rampUsers(1000) over (1 minutes))
        ).protocols(httpProtocol)
    }
}