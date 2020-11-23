package relay.service // 1

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class BasicSimulation extends Simulation { // 3

  val httpProtocol = http // 4
    .baseUrl("http://localhost:8080") // 5
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // 6
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

  val scn = scenario("BasicSimulation") // 7
    .exec(
      http("request_1") // 8
        .get("/relays")
    ) // 9
    .pause(5) // 10

  setUp( // 11
    scn.inject(atOnceUsers(5000)) // 12
    //    scn.inject(rampUsers(1000) over (1 minutes)) // 12
  ).protocols(httpProtocol) // 13
}
