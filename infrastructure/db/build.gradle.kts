plugins {
    `java`
    id("org.springframework.boot") version "3.0.3"
    id("io.spring.dependency-management") version "1.1.0"
    `maven-publish`
    `java-library`
}

val jar: Jar by tasks
val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks

bootJar.enabled = false
jar.enabled = true

group = "ru.relay.infrastructure"
version = "0.01"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("commons-beanutils:commons-beanutils:1.9.4")
    implementation(project(":id"))
    api("org.springframework.boot:spring-boot-starter-data-jpa")
    testImplementation("org.springframework.boot:spring-boot-starter-data-jpa")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}



publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = group.toString()
            artifactId = "db"
            version = version.toString()

            from(components["java"])
        }
    }
}

