plugins {
    id("application") // For java executable applications
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

application {
    mainClass = "com.gradlehero.themepark.RideStatusService"
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = application.mainClass
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}