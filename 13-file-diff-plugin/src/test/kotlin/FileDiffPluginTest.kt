import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File

class FileDiffPluginTest {

    @TempDir
    private lateinit var testProjectDir: File
    private lateinit var testFile1: File
    private lateinit var testFile2: File
    private lateinit var buildFile: File

    @BeforeEach
    fun setup() {
        testFile1 = File(testProjectDir, "testFile1.txt")
        testFile2 = File(testProjectDir, "testFile2.txt")
        buildFile = File(testProjectDir, "build.gradle.kts")

        buildFile.writeText(
            """
            plugins {
                id("me.whsv26.plugins.file-diff")
            }
            
            fileDiff {
                val dir = layout.projectDirectory.dir("${testProjectDir.path}")
                file1.set(dir.file("${testFile1.name}"))
                file2.set(dir.file("${testFile2.name}"))
            }
            """.trimIndent()
        )
    }

    @Test
    fun `can diff 2 files of same length`() {
        testFile1.createNewFile()
        testFile2.createNewFile()

        val result = GradleRunner.create()
            .withProjectDir(testProjectDir)
            .withArguments("fileDiff")
            .withPluginClasspath()
            .build()

        assertTrue(result.output.contains("testFile1.txt and testFile2.txt have the same size"))
        assertEquals(TaskOutcome.SUCCESS, result.task(":fileDiff")?.outcome)
    }

    @Test
    fun `can diff 2 files where 1st file larger than 2nd`() {
        testFile1.writeText("Some text")
        testFile2.createNewFile()

        val result = GradleRunner.create()
            .withProjectDir(testProjectDir)
            .withArguments("fileDiff")
            .withPluginClasspath()
            .build()

        assertTrue(result.output.contains("testFile1.txt was larger"))
        assertEquals(TaskOutcome.SUCCESS, result.task(":fileDiff")?.outcome)
    }

    @Test
    fun `can diff 2 files where 2nd file larger than 1st`() {
        testFile1.createNewFile()
        testFile2.writeText("Some text")

        val result = GradleRunner.create()
            .withProjectDir(testProjectDir)
            .withArguments("fileDiff")
            .withPluginClasspath()
            .build()

        assertTrue(result.output.contains("testFile2.txt was larger"))
        assertEquals(TaskOutcome.SUCCESS, result.task(":fileDiff")?.outcome)
    }
}