subprojects {
    apply(plugin = "java")
    extensions.configure<JavaPluginExtension> {
        toolchain {
            languageVersion = JavaLanguageVersion.of(
                rootProject.libs.versions.java.get().toInt()
            )
        }
    }
}