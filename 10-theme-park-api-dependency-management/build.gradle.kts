plugins {
    id("java")
    id("org.springframework.boot") version("3.3.2")
    id("io.spring.dependency-management") version "1.1.7" // dependency versions
}

repositories {
    mavenCentral()
}

dependencies {
    // No need to specify dependency version
    implementation("org.springframework.boot:spring-boot-starter-web")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}
