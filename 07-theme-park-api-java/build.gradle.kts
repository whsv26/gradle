plugins {
    id("java")

    // JAR plugin won't include "spring-boot-starter-web" classes into JAR
    // so we need fat JAR created by shadow plugin,
    // but it won't work nonetheless
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:3.3.2")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

tasks.shadowJar {
    // Shadow JAR will exit after start
    // Shadow JAR will run your app as a plain executable JAR
    // instead of a Spring Boot–style fat JAR with its special loader layout
    archiveBaseName.set("07-theme-park-api-java")
    manifest {
        attributes("Main-Class" to "com.gradlehero.themepark.ThemeParkApplication")
    }
}