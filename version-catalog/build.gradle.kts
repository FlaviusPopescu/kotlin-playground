import com.vanniktech.maven.publish.SonatypeHost
import org.gradle.accessors.dm.LibrariesForLibs

plugins {
    `version-catalog`
    id("com.vanniktech.maven.publish") version "0.29.0"
}

group = "dev.flavius"
version = "0.0.1"

catalog {
    versionCatalog {
        from(files("$rootDir/gradle/libs.versions.toml"))
    }
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL, automaticRelease = true)
    signAllPublications()
    coordinates("dev.flavius", "version-catalog", version.toString())
    pom {
        name.set("Version Catalog")
        description.set("Version Catalog")
        val libraryLocation = "github.com/FlaviusPopescu/kotlin-playground"
        url.set("https://$libraryLocation")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("flavius")
                name.set("Flavius Popescu")
                url.set("https://github.com/FlaviusPopescu/")
            }
        }
        scm {
            url.set("https://$libraryLocation")
            connection.set("scm:git:git://$libraryLocation.git")
            developerConnection.set("scm:git:ssh://git@$libraryLocation.git")
        }
    }
}
