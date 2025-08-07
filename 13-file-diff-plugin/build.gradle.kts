group = "me.whsv26.plugins"
version = "0.1.0"

plugins {
    `kotlin-dsl` // lets you write your plugin in Kotlin
    `maven-publish` // so you can push to mavenLocal()
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

gradlePlugin {
    plugins {
        create("file-diff-plugin") {
            id = "me.whsv26.plugins.file-diff"
            implementationClass = "FileDiffPlugin"
        }
    }
}

publishing {
    repositories {
        mavenLocal()
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}