plugins {
    id("java")
    id("io.spring.dependency-management") version "1.1.7"
}

group = "ru.relay.infrastructure"
version = "0.0.1"

extra["testcontainersVersion"] = "2.0.3"

tasks {
    jar {
        enabled = true
    }
}

dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:${property("testcontainersVersion")}")
    }
}

repositories {
    mavenCentral()
}



dependencies {
//    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation(platform("org.junit:junit-bom:6.0.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.testcontainers:postgresql:1.21.3")
    implementation("org.springframework.boot:spring-boot-devtools:4.0.2")
    implementation("org.springframework.boot:spring-boot-autoconfigure:4.0.2")
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = group.toString()
            artifactId = "local-postgres"
            version = version.toString()

            from(components["java"])
        }
    }
}