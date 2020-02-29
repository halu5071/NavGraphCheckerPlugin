package io.moatwel.android.plugin.navigation

import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException

class NavigationToolsPluginTest {

    @Rule
    @JvmField
    val testProjectDir = TemporaryFolder()

    private lateinit var settingsFile: File
    private lateinit var buildFile: File

    @Before
    fun setup() {
        settingsFile = testProjectDir.newFile("settings.gradle")
        buildFile = testProjectDir.newFile("build.gradle")
    }

    @Test
    fun test() {
        writeFile(settingsFile, "rootProject.name = 'hello-world'")
        val buildFileContent = """
            plugins {
                id 'io.moatwel.android.plugin.navigation-tools'
            }
        """.trimIndent()
        writeFile(buildFile, buildFileContent)

        val result = GradleRunner.create()
            .withProjectDir(testProjectDir.root)
//            .withArguments("helloWorld")
            .build()

        assertTrue(result.output.contains("Hello world!"))
        assertEquals(TaskOutcome.SUCCESS, result.task(":helloWorld")?.outcome)
    }

    @Throws(IOException::class)
    private fun writeFile(destination: File, content: String) {
        var output: BufferedWriter? = null
        try {
            output = BufferedWriter(FileWriter(destination))
            output.write(content)
        } finally {
            output?.close()
        }
    }
}