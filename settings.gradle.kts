@file:Suppress("UnstableApiUsage")

import org.gradle.api.initialization.dsl.VersionCatalogBuilder.PluginAliasBuilder

rootProject.name = "kotlin-playground"

pluginManagement {
    repositories {
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
    versionCatalogs {
        create("libs") {
            from("dev.flavius:version-catalog:+")
            // define simplified accessors for convention plugins; the version is not required.
            fun PluginAliasBuilder.withoutVersion() = version("unspecified")
            plugin(
                "convention-multiplatformComposeApp",
                "dev.flavius.build.convention.application.multiplatform.compose",
            ).withoutVersion()
            plugin(
                "convention-multiplatformComposeLib",
                "dev.flavius.build.convention.library.multiplatform.compose",
            ).withoutVersion()
        }
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

includeBuild("build-conventions")

include(":app")