```shell
15-grpc/gradlew -p 15-grpc :proto-java:publishToMavenLocal :proto-kotlin:publishToMavenLocal
15-grpc/gradlew -p 15-grpc :grpc-server-java:run
15-grpc/gradlew -p 15-grpc :grpc-server-kotlin:run
```

```shell
brew install grpcui
grpcui -plaintext localhost:50051
```