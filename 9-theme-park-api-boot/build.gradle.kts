plugins {
    id("java")
    id("org.springframework.boot") version("3.3.2")
}

repositories {
    mavenCentral()
}

dependencies {
    // Required to specify dependency version
    // Cause no platform enforced
    implementation("org.springframework.boot:spring-boot-starter-web:3.3.2")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}
