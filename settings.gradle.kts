@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
        maven {
            name = "centralManualTesting"
            url = uri("https://central.sonatype.com/api/v1/publisher/deployments/download/")
            credentials(HttpHeaderCredentials::class)
            authentication.create<HttpHeaderAuthentication>("header")
        }
    }
    versionCatalogs {
        create("centralManualTestingLibs") {
            from("dev.flavius:version-catalog:+")
        }
    }
}

rootProject.name = "version-catalog"

include("version-catalog")
