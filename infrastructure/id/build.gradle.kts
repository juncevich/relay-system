group = "ru.relay.infrastructure"
version = "0.01"

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = group.toString()
            artifactId = "id"
            version = version.toString()

            from(components["java"])
        }
    }
}