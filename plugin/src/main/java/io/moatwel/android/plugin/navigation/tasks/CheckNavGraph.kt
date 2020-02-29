package io.moatwel.android.plugin.navigation.tasks

import io.moatwel.android.plugin.navigation.data.NavigationFragment
import io.moatwel.android.plugin.navigation.exception.IllegalNavGraphException
import io.moatwel.android.plugin.navigation.ext.mainDir
import io.moatwel.android.plugin.navigation.ext.resourceDir
import io.moatwel.android.plugin.navigation.ext.toPackage
import org.gradle.api.Project
import org.gradle.api.Task
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler
import java.io.File
import java.nio.file.Files
import java.util.stream.Collectors
import javax.xml.parsers.SAXParserFactory

object CheckNavGraph {

    private const val ELEMENT_FRAGMENT = "fragment"
    private const val ELEMENT_FRAGMENT_NAME = "android:name"

    private const val EXTENSION_JAVA = "java"
    private const val EXTENSION_KOTLIN = "kt"

    private const val DIR_NAME_NAVIGATION = "navigation"

    fun register(project: Project): Task {
        return project.task("checkNavGraph").doLast {
            val fragmentList = createNavFragments(project)

            val srcPackageList = findSrcFiles(project).map { it.toPackage(project.mainDir) }

            val result = mutableListOf<NavigationFragment>()
            fragmentList.forEach {
                val isExist = srcPackageList.contains(it.fragmentName)
                if (isExist.not()) {
                    result.add(it)
                }
            }

            if (result.isNotEmpty()) {
                throw IllegalNavGraphException("Error in NavGraph. ${result[0].xmlFile.name}")
            }

            fragmentList.forEach {
                println("Xml File: ${it.xmlFile.name}, Fragment: ${it.fragmentName}")
            }

            srcPackageList.forEach {
                println("File with package: $it")
            }
        }
    }

    private fun createNavFragments(project: Project): List<NavigationFragment> {
        val fragmentList = mutableListOf<NavigationFragment>()

        val navigationDir = project.resourceDir
            .resolve(DIR_NAME_NAVIGATION)

        if (navigationDir.exists().not()) {
            return fragmentList
        }

        // Nullable
        val navigationXml = navigationDir.listFiles()
        val xmlParser = SAXParserFactory.newInstance().newSAXParser()
        navigationXml?.forEach { navXml ->
            xmlParser.parse(navXml, object : DefaultHandler() {
                override fun startElement(
                    uri: String?,
                    localName: String?,
                    qName: String?,
                    attributes: Attributes?
                ) {
                    if (qName == ELEMENT_FRAGMENT && attributes != null) {
                        // Nullable
                        val fragmentAttr = attributes.getValue(ELEMENT_FRAGMENT_NAME)
                        fragmentAttr?.let {
                            val navigationFragment = NavigationFragment(navXml, fragmentAttr)
                            fragmentList.add(navigationFragment)
                        }
                    }
                }
            })
        }

        return fragmentList
    }

    private fun findSrcFiles(project: Project): List<File> {
        val fileList = mutableListOf<File>()

        val mainDir = project.mainDir
        if (mainDir.exists().not()) {
            return fileList
        }

        val files = Files.walk(mainDir.toPath())
            .filter {
                val ext = it.toFile().extension
                ext == EXTENSION_JAVA || ext == EXTENSION_KOTLIN
            }
            .map { it.toFile() }
            .collect(Collectors.toList())

        fileList.addAll(files)

        return fileList
    }
}