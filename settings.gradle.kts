pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "NodeJSAndroid"
include(":NodeJS")
include(":sampleappwithjsfile")
include(":sampleappwithjscode")
include(":sampleappwithcomplexjs")
