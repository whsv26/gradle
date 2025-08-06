plugins {
    id("application") // For java executable applications
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("org.apache.commons:commons-lang3:3.18.0") // For StringUtils.trim

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.7.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.2")
}

application {
    mainClass = "com.gradlehero.themepark.RideStatusService"
}

testing {
    suites {
        register<JvmTestSuite>("integrationTest") {
            dependencies {
                implementation(project())
            }
            targets.all {
                testTask.configure {
                    shouldRunAfter(tasks.named("test"))
                }
            }
        }
    }
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = application.mainClass
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.register<JavaExec>("runJar") {
    group = "application"
    mainClass = application.mainClass // JavaExec is ignoring the manifest Main-Class
    args("teacups")
    classpath(
        tasks.named("jar").map { it.outputs },

        // If you omit that, JavaExec only sees your own and JDK classes
        // ClassNotFoundException will be thrown for library code usage
        // e.g. for StringUtils.trim call
        configurations.runtimeClasspath
    )
}

tasks.named("check") {
    // to run "integrationTest" in addition to "test" before "check" is running
    dependsOn(tasks.named("integrationTest"))
}