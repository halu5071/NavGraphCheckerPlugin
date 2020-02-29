package io.moatwel.android.plugin.navigation.ext

import io.moatwel.android.plugin.navigation.NavigationToolsPluginExtension
import org.gradle.api.Project

val Project.navExtension
    get() = this.extensions.getByType(NavigationToolsPluginExtension::class.java)
