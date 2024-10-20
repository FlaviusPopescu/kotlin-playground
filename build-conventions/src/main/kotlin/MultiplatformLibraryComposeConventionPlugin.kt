import com.android.build.api.dsl.LibraryExtension
import dev.flavius.build.JAVA_VERSION
import dev.flavius.build.configureAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree

class MultiplatformLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.library")
            apply(plugin = "org.jetbrains.compose")
            apply(plugin = "org.jetbrains.kotlin.multiplatform")
            apply(plugin = "org.jetbrains.kotlin.plugin.compose")

            extensions.configure<KotlinMultiplatformExtension> {
                jvmToolchain(JAVA_VERSION)
                androidTarget {
                    @OptIn(ExperimentalKotlinGradlePluginApi::class)
                    instrumentedTestVariant.sourceSetTree.set(KotlinSourceSetTree.test)
                }
                jvm()
            }
            configureAndroid<LibraryExtension>()
        }
    }
}
