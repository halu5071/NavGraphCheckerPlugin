package io.moatwel.android.plugin.navigation.tasks

import io.moatwel.android.plugin.navigation.NavigationToolsPluginExtension
import io.moatwel.android.plugin.navigation.data.NavigationFragment
import io.moatwel.android.plugin.navigation.ext.navExtension
import org.gradle.api.Project
import org.gradle.api.Task
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler
import javax.xml.parsers.SAXParserFactory

object CheckNavGraph {

    private const val ELEMENT_FRAGMENT = "fragment"
    private const val ELEMENT_FRAGMENT_NAME = "android:name"

    fun register(project: Project): Task {
        return project.task("checkNavGraph").doLast {
            val fragmentList = createNavFragments(project)

            fragmentList.forEach {
                println("File: ${it.xmlFile.name}, Fragment: ${it.fragmentName}")
            }
        }
    }

    private fun createNavFragments(project: Project): List<NavigationFragment> {
        val fragmentList = mutableListOf<NavigationFragment>()

        val navigationDir = project.rootDir
            .resolve(project.navExtension.appDir)
            .resolve("src")
            .resolve("main")
            .resolve("res")
            .resolve("navigation")

        if (navigationDir.exists().not()) {
            return fragmentList
        }

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
}