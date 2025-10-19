plugins {
    java
    id("maven-publish")
}

group = "ru.relay.infrastructure"
version = "unspecified"
java.sourceCompatibility = JavaVersion.VERSION_25

repositories {
    mavenCentral()
}



dependencies {

    testImplementation("org.junit.jupiter:junit-jupiter-api:6.0.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:6.0.0")
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")

    repositories {
        mavenCentral()
    }

    dependencies {
        compileOnly("org.projectlombok:lombok:1.18.42")
        annotationProcessor("org.projectlombok:lombok:1.18.42")
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}