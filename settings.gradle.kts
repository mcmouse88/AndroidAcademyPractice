@file:Suppress("UnstableApiUsage")

include(":ui-kit")


include(":ui-testing")


pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AndroidAcademyPractice"
include(
    ":starter-pack",
    ":starter-pack-2",
    ":animation"
)
 