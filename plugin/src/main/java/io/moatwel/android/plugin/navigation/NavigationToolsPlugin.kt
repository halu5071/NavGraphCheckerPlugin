package io.moatwel.android.plugin.navigation

import io.moatwel.android.plugin.navigation.tasks.CheckNavGraph
import org.gradle.api.Plugin
import org.gradle.api.Project

class NavigationToolsPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val checkNavGraphTask = CheckNavGraph.register(project)
        project.afterEvaluate {
            it.tasks.filter { task -> task.name.contains("compile") }
                .forEach { task -> task.finalizedBy(checkNavGraphTask) }
        }
    }
}