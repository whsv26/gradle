import org.apache.tools.ant.filters.ReplaceTokens

plugins {
    id("base") // Defines a basic project lifecycle (build and clean tasks)
}

tasks.register<Copy>("generateDescriptions") {
    group = "build"
    from("descriptions")
    into(layout.buildDirectory.dir("descriptions"))
    filter<ReplaceTokens>("tokens" to mapOf(
        "THEME_PARK_NAME" to "Grelephant's Wonder World"
    ))
}

tasks.register<Zip>("zipDescriptions") {
    group = "build"
    from(tasks.named("generateDescriptions"))
    destinationDirectory = layout.buildDirectory
    archiveFileName = "descriptions.zip"
}