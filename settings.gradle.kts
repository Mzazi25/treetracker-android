pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        jcenter()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        jcenter()
    }
}
rootProject.name = "treetracker-android"
include(":app")
enableFeaturePreview("VERSION_CATALOGS")
