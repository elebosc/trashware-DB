plugins {
    java
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    mavenCentral()
}

val javaFXModules = listOf(
    "base",
    "controls",
    "fxml",
    "swing",
    "graphics"
)

val supportedPlatforms = listOf("linux", "mac", "win")

dependencies {
    // JavaFX
    val javaFxVersion = 19
    for (platform in supportedPlatforms) {
        for (module in javaFXModules) {
            implementation("org.openjfx:javafx-$module:$javaFxVersion:$platform")
        }
    }

    // Hibernate
    implementation("org.hibernate:hibernate-core:6.2.7.Final")

    // MySQL
    implementation("mysql:mysql-connector-java:8.0.33")

    // JUnit
    val jUnitVersion = "5.9.2"
    testImplementation("org.junit.jupiter:junit-jupiter-api:$jUnitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$jUnitVersion")

    // SLF4J
    implementation("org.slf4j:slf4j-api:2.0.6")
    // Logback backend for SLF4J
    runtimeOnly("ch.qos.logback:logback-classic:1.4.5")

    // Datafaker
    implementation("net.datafaker:datafaker:2.0.1")
}

application {
    // Definition of the main class for the application
    mainClass.set("it.unibo.trashware.App")
}
