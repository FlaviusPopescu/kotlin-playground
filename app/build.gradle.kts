import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.convention.multiplatformComposeApp)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.uiTooling)
            implementation(libs.kermit)
            implementation(libs.kotlinx.datetime)
            implementation(libs.ktor.clientContentNegotiation)
            implementation(libs.ktor.clientCore)
            implementation(libs.ktor.clientLogging)
            implementation(libs.ktor.serializationKotlinxJson)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.uiTest)
        }
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.lifecycle.viewModelCompose)
            implementation(libs.ktor.clientAndroid)
        }
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.ktor.clientOkhttp)
                implementation(libs.logbackClassic)
            }
        }
        val desktopTest by getting
    }
}

android.namespace = "dev.flavius.playground.kotlin"

compose.desktop {
    application {
        mainClass = "dev.flavius.playground.multiplatform.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "dev.flavius.playground.multiplatform"
            packageVersion = "1.0.0"
        }
    }
}
