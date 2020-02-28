package io.moatwel.android.plugin.navigation.tasks

import org.gradle.api.Project
import org.gradle.api.Task

object CheckNavGraph {

    fun register(project: Project): Task {
        return project.task("checkNavGraph").doLast {

        }
    }
}