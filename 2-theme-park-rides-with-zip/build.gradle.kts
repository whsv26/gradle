import org.apache.tools.ant.filters.ReplaceTokens

plugins {
    base // Defines a basic project lifecycle (build and clean tasks)
}

tasks.register<Copy>("generateDescriptions") {
    group = "build"
    description = "Generate descriptions"
    from("descriptions")
    into(layout.buildDirectory.dir("descriptions"))
    filter<ReplaceTokens>("tokens" to mapOf(
        "THEME_PARK_NAME" to "Grelephant's Wonder World"
    ))
}

tasks.register<Zip>("zipDescriptions") {
    group = "build"
    description = "Zip descriptions"
    from(layout.buildDirectory.dir("descriptions"))
    destinationDirectory = layout.buildDirectory
    archiveFileName = "descriptions.zip"
}