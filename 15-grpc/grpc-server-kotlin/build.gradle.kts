plugins {
    application
    alias(libs.plugins.kotlin.jvm)
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("me.whsv26:proto-kotlin:1.0.0") // Generated kotlin code from proto files
    implementation(libs.grpc.netty) // Server implementation via netty
    implementation(libs.grpc.services) // Server reflection api
}

application {
    mainClass.set("me.whsv26.GreeterAppKt")
}