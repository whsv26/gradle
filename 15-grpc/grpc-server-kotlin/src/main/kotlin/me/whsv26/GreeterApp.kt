package me.whsv26

import io.grpc.Server
import io.grpc.ServerBuilder
import io.grpc.protobuf.services.ProtoReflectionService

class GreeterServer(port: Int) {

    val server: Server =
        ServerBuilder
            .forPort(port)
            .addService(GreeterService())
            .addService(ProtoReflectionService.newInstance())
            .build()

    fun start() {
        server.start()
        Runtime.getRuntime().addShutdownHook(Thread { stop() })
    }

    private fun stop() {
        server.shutdown()
    }

    fun blockUntilShutdown() {
        server.awaitTermination()
    }

    internal class GreeterService: GreeterGrpcKt.GreeterCoroutineImplBase() {
        override suspend fun sayHello(request: HelloRequest): HelloReply =
            helloReply {
                message = "Hello ${request.name}"
            }
    }
}

fun main() {
    val port = 50051
    val server = GreeterServer(port)
    server.start()
    server.blockUntilShutdown()
}