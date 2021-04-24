import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.4.32"
    kotlin("plugin.spring") version "1.4.32"
    id("com.google.cloud.tools.jib") version "3.0.0"
    jacoco
}

group = "com.relay"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_16

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

extra["springCloudVersion"] = "2020.0.2"
extra["testcontainersVersion"] = "1.15.3"

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
    implementation("org.springframework.cloud:spring-cloud-sleuth-zipkin")
    implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-reactor-resilience4j")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("org.springframework.cloud:spring-cloud-starter-sleuth")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
//    testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.testcontainers:junit-jupiter")
//    testImplementation("org.testcontainers:mongodb")
}

dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:${property("testcontainersVersion")}")
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

jib {
    from {
        image = "openjdk:16-alpine"
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

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}



jacoco {
    toolVersion = "0.8.6"
//    reportsDirectory.set(layout.buildDirectory.dir("customJacocoReportDir"))
}



tasks.jacocoTestReport {
    reports {
        xml.isEnabled = true
        csv.isEnabled = true
//        html.destination = layout.buildDirectory.dir("jacocoHtml").get().asFile
    }
}




tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}
tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
}






