package io.moatwel.android.plugin.navigation

import io.moatwel.android.plugin.navigation.tasks.CheckNavGraph
import org.gradle.api.Plugin
import org.gradle.api.Project

class NavigationToolsPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.extensions.create(
            NavigationToolsPluginExtension.NAME,
            NavigationToolsPluginExtension::class.java
        )
        val checkNavGraphTask = CheckNavGraph.register(project)
        println("task: $checkNavGraphTask")
        project.tasks.getByName("build")
    }
}