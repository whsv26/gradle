import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.File

abstract class FileDiffTask : DefaultTask() {

    @get:InputFile
    abstract val file1: RegularFileProperty

    @get:InputFile
    abstract val file2: RegularFileProperty

    @get:Input
    val outputPath: String = project.layout.buildDirectory.file("diff.txt").get().asFile.path

    @get:OutputFile
    val outputFile: File = File(outputPath)

    @TaskAction
    fun diff() {
        val file1Size = file1.get().asFile.length()
        val file2Size = file2.get().asFile.length()
        val file1Name = file1.get().asFile.name
        val file2Name = file2.get().asFile.name

        if (file1Size == file2Size) {
            val text = "$file1Name and $file2Name have the same size\n"
            outputFile.writeText(text, Charsets.UTF_8)
            print(text)
        } else if (file1Size > file2Size) {
            val text = "$file1Name was larger\n"
            outputFile.writeText(text, Charsets.UTF_8)
            print(text)
        } else {
            val text = "$file2Name was larger\n"
            outputFile.writeText(text, Charsets.UTF_8)
            print(text)
        }
    }
}