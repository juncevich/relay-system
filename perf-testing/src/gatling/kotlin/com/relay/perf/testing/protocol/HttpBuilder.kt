package com.relay.perf.testing.protocol

import io.gatling.javaapi.http.HttpDsl
import io.gatling.javaapi.http.HttpProtocolBuilder

class HttpBuilder {

    private val targetHttpUrl = System.getenv("base_url") ?: "http://localhost:8080"
    private val targetWsUrl = System.getenv("base_ws_url") ?: "ws://localhost:8080"

    fun build(): HttpProtocolBuilder = HttpDsl.http
        .enableHttp2()
        .shareConnections()
        .baseUrl(targetHttpUrl)
        .wsBaseUrl(targetWsUrl)
        .acceptHeader("application/json,text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
        .acceptEncodingHeader("gzip, deflate")
        .acceptLanguageHeader("en-US,en;q=0.5")
        .header("connection", "keep-alive")
        .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:105.0) Gecko/20100101 Firefox/105.0")
        .disableCaching()

}