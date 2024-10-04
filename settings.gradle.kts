@file:Suppress("UnstableApiUsage")

dependencyResolutionManagement {
    repositories {
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
