plugins {
    java
    alias(libs.plugins.spring.boot)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(enforcedPlatform(libs.spring.boot.dependencies))
    implementation(project(":service"))
    implementation(libs.bundles.spring.boot.web) // No need to specify dependency version
}
