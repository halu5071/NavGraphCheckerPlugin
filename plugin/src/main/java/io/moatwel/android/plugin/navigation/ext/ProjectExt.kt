package io.moatwel.android.plugin.navigation.ext

import io.moatwel.android.plugin.navigation.NavigationToolsPluginExtension
import org.gradle.api.Project
import java.io.File

val Project.navExtension: NavigationToolsPluginExtension
    get() = this.extensions.getByType(NavigationToolsPluginExtension::class.java)

val Project.javaDir: File
    get() = mainDir.resolve("java")

val Project.kotlinDir: File
    get() = mainDir.resolve("kotlin")

val Project.resourceDir: File
    get() = mainDir.resolve("res")

val Project.mainDir: File
    get() = this.projectDir.resolve("src").resolve("main")