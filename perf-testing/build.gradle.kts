plugins {
//    id 'java'
//    id 'io.gatling.gradle' version '3.4.1'
    kotlin("jvm") version "2.1.0"
    idea
    id("io.gatling.gradle") version "3.13.3"
}

group = "com.relay.perf.testing"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val gatlingVersion = "3.13.3"
val apacheCommonsVersion = "3.17.0"
val postgresDriverVersion = "42.7.5"
val tinkoffGatlingJdbcPluginVersion = "0.10.3"
val kotlinLoggingVersion = "3.0.5"
val kotlinReflectVersion = "2.1.0"

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.gatling:gatling-core-java:$gatlingVersion")
    implementation("io.gatling:gatling-http-java:$gatlingVersion")
    implementation("org.apache.commons:commons-lang3:$apacheCommonsVersion")
    implementation("org.postgresql:postgresql:$postgresDriverVersion")
    gatlingImplementation("org.apache.commons:commons-lang3:$apacheCommonsVersion")
    gatlingImplementation("org.postgresql:postgresql:$postgresDriverVersion")
    gatlingImplementation("ru.tinkoff:gatling-jdbc-plugin_2.13:$tinkoffGatlingJdbcPluginVersion")
    gatlingImplementation("io.github.microutils:kotlin-logging:$kotlinLoggingVersion")
    gatlingImplementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinReflectVersion")
}

gatling {
//    jvmArgs = listOf("-server", "-Xms16G", "-Xmx16G", "-XX:-MaxFDLimit")

    // WARNING: options below only work when logback config file isn't provided
//    logLevel = "TRACE" // logback root level
//    logLevel = "WARN" // logback root level
//    logHttp = io.gatling.gradle.LogHttp.FAILURES // set to 'ALL' for all HTTP traffic in TRACE, 'FAILURES' for failed HTTP traffic in DEBUG
}
