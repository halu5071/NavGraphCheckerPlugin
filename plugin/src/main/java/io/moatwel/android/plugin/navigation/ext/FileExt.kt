package io.moatwel.android.plugin.navigation.ext

import java.io.File

fun File.toPackage(mainDir: File): String {
    val len = mainDir.absolutePath.length
    val path = this.absolutePath
        .substring(len + 1)
        .substringBeforeLast(".")
        .replace("/", ".")
    return when {
        path.contains("java") -> path.substring(5) // java.
        path.contains("kotlin") -> path.substring(7) // kotlin.
        else -> path
    }
}