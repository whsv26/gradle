plugins {
    // Kotlin language support
    alias(libs.plugins.kotlin.jvm)

    // So you can push to mavenLocal()
    id("maven-publish")

    // For java code generation from proto files
    alias(libs.plugins.protobuf)
}

repositories {
    mavenCentral()
}

dependencies {
    // Provides support for client and server stubs generated from .proto files
    // Provides core base classes for stubs used in generated Grpc classes
    api(libs.grpc.stub)

    // For gRPC integrating into protobuf
    // Provides protobuf marshalling logic for gRPC
    api(libs.grpc.protobuf)

    // TODO: DO IT REALLY NEEDED
    //
    // Transitively provides "protobuf-java" functionality:
    // 1. Message decoding (parseFrom)
    // 2. Message encoding (toByteArray)
    // 2. Equality, hashing, merging
    //
    // And additional functionality:
    // 1. Protobuf to JSON converters
    // 2. Timestamp/Duration utils
    api(libs.protobuf.java.util)


    // - Kotlin-friendly generated code from .proto files
    // - Support for Kotlin idioms (null-safety, collections, etc.)
    // - Extensions and helpers that make using Protobufs in Kotlin more natural
    api(libs.protobuf.kotlin)

    // - Coroutine-based gRPC client and server stubs in Kotlin
    // - A Kotlin-friendly alternative to Java’s StreamObserver API
    // - Integration with Kotlin’s suspend functions
    api(libs.grpc.kotlin.stub)

    // Kotlin coroutines standart library
    api(libs.kotlinx.coroutines.core)
}

kotlin {
    // Ensures compatibility: your Kotlin code will run on JVMs that support Java 8
    jvmToolchain(8)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
    kotlinOptions {
        // Allows using opt-in Kotlin APIs (marked with @RequiresOptIn)
        // For using experimental APIs from libraries like kotlinx.coroutines, grpc-kotlin, etc.
        freeCompilerArgs = listOf("-opt-in=kotlin.RequiresOptIn")
    }
}

sourceSets {
    main {
        proto {
            // Where are .proto files placed?
            srcDir("../proto/src/main/proto")
        }
    }
}

protobuf {
    protoc {
        // Pulls protoc binary used to compile .proto files
        artifact = libs.protoc.asProvider().get().toString()
    }
    plugins {
        // Provides Java gRPC support for protoc via plugin extension
        // Registers a plugin globally by name ("grpc") and tells where to find its executable
        create("grpc") {
            artifact = libs.protoc.gen.grpc.java.get().toString()
        }

        // Provides Kotlin gRPC support for protoc via plugin extension
        // Registers a plugin globally by name ("grpckt") and tells where to find its executable
        create("grpckt") {
            artifact = libs.protoc.gen.grpc.kotlin.get().toString() + ":jdk8@jar"
        }
    }
    generateProtoTasks {
        all().configureEach {
            plugins {
                // Tells each generateProto task to actually use "grpc" plugin when generating Java code
                create("grpc")

                // Tells each generateProto task to actually use "grpckt" plugin when generating Kotlin code
                create("grpckt")
            }
            builtins {
                // Generate Kotlin classes from Protobuf messages
                create("kotlin")
            }
        }
    }
}

publishing {
    repositories {
        mavenLocal()
    }
    publications {
        create<MavenPublication>("kotlinProto") {
            from(components["java"]) // Yes, for JVM target it is "java"
            artifactId = "proto-kotlin"
            groupId = "me.whsv26"
            version = "1.0.0"
        }
    }
}