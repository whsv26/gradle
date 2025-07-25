import org.apache.tools.ant.filters.ReplaceTokens

tasks.register<Copy>("generateDescriptions") {
    from("descriptions")
    into(layout.buildDirectory.dir("descriptions"))
    filter<ReplaceTokens>("tokens" to mapOf(
        "THEME_PARK_NAME" to "Grelephant's Wonder World"
    ))
}

tasks.register<Zip>("zipDescriptions") {
    from(layout.buildDirectory.dir("descriptions"))
    destinationDirectory = layout.buildDirectory
    archiveFileName = "descriptions.zip"
}