package io.moatwel.android.plugin.navigation.ext

import org.gradle.api.Project
import java.io.File

val Project.resourceDir: File
    get() = mainDir.resolve("res")

val Project.mainDir: File
    get() = this.projectDir.resolve("src").resolve("main")