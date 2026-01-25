plugins {
    id("org.springframework.boot") version "4.0.2"
    id("io.spring.dependency-management") version "1.1.7"
    kotlin("jvm") version "2.2.20"
    kotlin("plugin.spring") version "2.2.20"
    id("com.google.cloud.tools.jib") version "3.4.3"
    id("org.sonarqube") version "6.0.1.5171"
    jacoco
}

group = "com.relay"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(24)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

extra["springCloudVersion"] = "2020.0.2"
extra["testcontainersVersion"] = "1.17.6"

sonarqube {
    properties {
        property("sonar.login", System.getenv("SONAR_DATA_SERVICE_TOKEN"))
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.organization", "relay-service")
        property("sonar.projectKey", "comment-data-service")
        property("sonar.jacoco.reportPaths", "build/reports/jacoco/test/jacocoTestReport.xml")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-cache")
//    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
//    implementation("org.springframework.cloud:spring-cloud-sleuth-zipkin")
//    implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-reactor-resilience4j")
//    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
//    implementation("org.springframework.cloud:spring-cloud-starter-sleuth")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
//    testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
//    testImplementation("org.testcontainers:mongodb")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:${property("testcontainersVersion")}")
//        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

jib {
    from {
        image = "eclipse-temurin:25-alpine"
    }
    to {
        image = "alexunc/relay/comments"
        auth {
            username = System.getenv("DOCKER_LOGIN")
            password = System.getenv("DOCKER_PASS")
        }
        tags = setOf("latest")
    }
    container {
//        jvmFlags = ['-Dmy.property=example.value', '-Xms512m', '-Xdebug']
        ports = listOf("9090", "8080")
//        labels = [key1:'value1', key2:'value2']
//        format = 'OCI'
    }
}

tasks.bootJar {
    archiveFileName.set("app.jar")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}



jacoco {
    toolVersion = "0.8.14"
//    reportsDirectory.set(layout.buildDirectory.dir("customJacocoReportDir"))
}



tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
        html.required.set(true)
//        html.destination = layout.buildDirectory.dir("jacocoHtml").get().asFile
    }
}




tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}
tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
}






