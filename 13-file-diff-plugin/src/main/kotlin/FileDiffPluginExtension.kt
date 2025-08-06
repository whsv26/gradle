import org.gradle.api.file.RegularFileProperty
import org.gradle.api.model.ObjectFactory

open class FileDiffPluginExtension(objects: ObjectFactory) {
    val file1: RegularFileProperty = objects.fileProperty()
    val file2: RegularFileProperty = objects.fileProperty()
}