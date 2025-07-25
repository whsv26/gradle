import org.apache.tools.ant.filters.ReplaceTokens

tasks.register("generateDescriptions", Copy::class) {
    from("descriptions")
    into(layout.buildDirectory.dir("descriptions"))
    filter<ReplaceTokens>("tokens" to mapOf(
        "THEME_PARK_NAME" to "Grelephant's Wonder World"
    ))
}