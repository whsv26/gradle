plugins {
    application
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("me.whsv26:proto-java:1.0.0") // Generated java code from proto files
    implementation(libs.grpc.netty) // Server implementation via netty
    implementation(libs.grpc.services) // Server reflection api
}

application {
    mainClass = "me.whsv26.GreeterServer"
}
