package com.relay.perf.testing.simulation

import com.relay.perf.testing.protocol.HttpBuilder
import io.gatling.javaapi.core.CoreDsl.StringBody
import io.gatling.javaapi.core.CoreDsl.atOnceUsers
import io.gatling.javaapi.core.CoreDsl.scenario
import io.gatling.javaapi.core.Simulation
import io.gatling.javaapi.http.HttpDsl.http
import io.gatling.javaapi.http.HttpProtocolBuilder

class TestSimulation : Simulation() {

    private val httpProtocol: HttpProtocolBuilder = HttpBuilder().build()

    val scn = scenario("BasicSimulation") // 7
        .exec(
            http("request_1") // 8
                .post("/api/v2/metric")
                .header("x-viv-authId", "55429aa5-588f-4d5b-b001-b2492d028efd")
                .body(
                    StringBody(
                        """{
                                 "sensorMetrics": [
                                   {
                                     "measurementUnit": "mmol\/L",
                                     "id": "7FB2C7B0-2C52-457A-84EA-C423F604D795",
                                     "collection": "glucose",
                                     "source": "sensor",
                                     "value": 4.5,
                                     "createDateTime": "2021-01-05T16:40:26+05:00"
                                   },
                                   {
                                     "measurementUnit": "mmol\/L",
                                     "id": "7FB2C7B0-2C52-457A-84EA-C423F604D795",
                                     "collection": "glucose",
                                     "source": "sensor",
                                     "value": 4.5,
                                     "createDateTime": "2021-01-05T16:40:26+05:00"
                                   },
                                   {
                                     "measurementUnit": "mmol\/L",
                                     "id": "0F406FD9-8DD3-418A-977B-A1EA9137454E",
                                     "collection": "glucose",
                                     "source": "sensor",
                                     "value": 4.5126686096191406,
                                     "createDateTime": "2021-02-23T18:01:00Z"
                                   },
                                   {
                                     "measurementUnit": "mmol\/L",
                                     "id": "D68EF0BA-E7F3-4C06-8654-E9C8D3A555E7",
                                     "collection": "glucose",
                                     "source": "sensor",
                                     "value": 4.5189390182495117,
                                     "createDateTime": "2021-02-23T18:03:00Z"
                                   },
                                   {
                                     "measurementUnit": "mmol\/L",
                                     "id": "9339393e-de95-4885-8e10-747b4cc9777d",
                                     "collection": "glucose",
                                     "source": "sensor",
                                     "value": 4.5189390182495117,
                                     "createDateTime": "2021-02-23T18:03:00Z"
                                   },
                                   {
                                     "measurementUnit": "mmol\/L",
                                     "id": "250221fd-6cb5-42b2-8d0a-dc2264b13dab",
                                     "collection": "glucose",
                                     "source": "sensor",
                                     "value": 4.5,
                                     "createDateTime": "2021-01-05T16:40:26+05:00"
                                   },
                                   {
                                     "measurementUnit": "mmol\/L",
                                     "id": "33f3293b-bb19-40e1-b11c-b96b3beed509",
                                     "collection": "glucose",
                                     "source": "sensor",
                                     "value": 4.5,
                                     "createDateTime": "2021-01-05T16:40:26+05:00"
                                   },
                                   {
                                     "measurementUnit": "mmol\/L",
                                     "id": "2a40aa62-8490-4b51-ba7a-fbc15ec4a68e",
                                     "collection": "glucose",
                                     "source": "sensor",
                                     "value": 4.5126686096191406,
                                     "createDateTime": "2021-02-23T18:01:00Z"
                                   },
                                   {
                                     "measurementUnit": "mmol\/L",
                                     "id": "8088d766-1d7f-4673-98c1-8feb6a75a0b4",
                                     "collection": "glucose",
                                     "source": "sensor",
                                     "value": 4.5189390182495117,
                                     "createDateTime": "2021-02-23T18:03:00Z"
                                   },
                                   {
                                     "measurementUnit": "mmol\/L",
                                     "id": "15ca1ef6-118a-459b-aaac-21bd59cb296d",
                                     "collection": "glucose",
                                     "source": "sensor",
                                     "value": 4.5189390182495117,
                                     "createDateTime": "2021-02-23T18:03:00Z"
                                   }

                                 ]
                               }
                               """.trimMargin()
                    )
                ).asJson()
        ) // 9

    // 10
    init {
        setUp( // 11
            scn.injectOpen(atOnceUsers(10)) // 12
//        scn.inject(rampUsers(1000) over (1 minutes)) // 12
        ).protocols(httpProtocol) // 13
    }
}