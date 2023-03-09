plugins {
    `java`
    id("maven-publish")
}

group = "ru.relay.infrastructure"
version = "unspecified"

repositories {
    mavenCentral()
}



dependencies {

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")

    repositories {
        mavenCentral()
    }

    dependencies {
        compileOnly("org.projectlombok:lombok:1.18.26")
        annotationProcessor("org.projectlombok:lombok:1.18.26")
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}