@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
    }
}

rootProject.name = "kotlin-template"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include("app")
