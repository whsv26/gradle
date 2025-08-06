plugins {
    // For java libraries
    // You need to manually add manifest in order to run jar
    id("java")
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.7.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.2")
}

tasks.withType<Jar> {
    manifest {
        attributes(
            "Main-Class" to "com.gradlehero.themepark.RideStatusService"
        )
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}