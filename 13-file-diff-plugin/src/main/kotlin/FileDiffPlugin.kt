import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.*

class FileDiffPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val extension = project.extensions.create<FileDiffPluginExtension>("fileDiff")

        project.tasks.register<FileDiffTask>("fileDiff") {
            file1 = extension.file1
            file2 = extension.file2
        }
    }
}