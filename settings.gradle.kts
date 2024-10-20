@file:Suppress("UnstableApiUsage")

import org.gradle.api.initialization.dsl.VersionCatalogBuilder.PluginAliasBuilder


rootProject.name = "kotlin-playground"

pluginManagement {
    includeBuild("build-conventions")
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
            plugin("convention-composeAndroidApplication", "dev.flavius.build.convention.application.android.compose")
                .withoutVersion()
            plugin("convention-composeAndroidLibrary", "dev.flavius.build.convention.library.android.compose")
                .withoutVersion()
        }
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")
include(":feature-main")