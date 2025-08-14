plugins {
    // For "api" configuration
    id("java-library")

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


    // Transitively provides "protobuf-java" functionality:
    // 1. Message decoding (parseFrom)
    // 2. Message encoding (toByteArray)
    // 2. Equality, hashing, merging
    //
    // And additional functionality:
    // 1. Protobuf to JSON converters
    // 2. Timestamp/Duration utils
    api(libs.protobuf.java.util)

    if (JavaVersion.current().isJava9Compatible) {
        // To fix "cannot find symbol @javax.annotation.Generated"
        compileOnly("javax.annotation:javax.annotation-api:1.3.2")
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
    }
    generateProtoTasks {
        all().configureEach {
            plugins {
                // Tells each generateProto task
                // to actually use "grpc" plugin when generating code
                create("grpc")
            }
        }
    }
}

publishing {
    repositories {
        mavenLocal()
    }
    publications {
        create<MavenPublication>("javaProto") {
            from(components["java"])
            artifactId = "proto-java"
            groupId = "me.whsv26"
            version = "1.0.0"
        }
    }
}