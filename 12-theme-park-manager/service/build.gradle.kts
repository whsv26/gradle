plugins {
    // "java" plugin gives "implementation" configuration
    // "java-library" plugin is "java" plugin plus "api" configuration
    id("java-library")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.7.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.2")
}

tasks.withType<Test> {
    useJUnitPlatform()
}