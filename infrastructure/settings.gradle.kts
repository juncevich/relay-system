plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
rootProject.name = "infrastructure"

include(":id")
include(":db")
include(":local-postgres")
include(":rest")
