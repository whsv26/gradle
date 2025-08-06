group = "me.whsv26.plugins"
version = "0.1.0"

plugins {
    `kotlin-dsl`         // lets you write your plugin in Kotlin
    `maven-publish`      // so you can push to mavenLocal()
//    `java-gradle-plugin` // adds plugin marker metadata and the pluginMaven publication
}

repositories {
    mavenCentral()
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
