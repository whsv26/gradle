plugins {
    id("me.whsv26.plugins.file-diff") version "0.1.0"
}

fileDiff {
    val dir = layout.projectDirectory.dir("files")
    file1.set(dir.file("a.txt"))
    file2.set(dir.file("b.txt"))
}