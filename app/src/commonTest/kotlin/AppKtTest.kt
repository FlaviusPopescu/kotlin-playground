import androidx.compose.ui.test.*
import kotlin_template.app.generated.resources.Res
import kotlin_template.app.generated.resources.logo_content_description
import kotlin.test.Test

/**
 * To run the test:
 * - on Android:
 *      ./gradlew :app:connectedAndroidTest
 * - on Desktop:
 *      ./gradlew :composeApp:desktopTest
 *      OR
 *      use the gutter button in the IDE and select 'desktop'
 */
@OptIn(ExperimentalTestApi::class)
class AppKtTest {
    @Test
    fun test() = runComposeUiTest {
        setContent {
            App()
        }
        val logoContentDescription = Res.string.logo_content_description.toString()
        onNodeWithContentDescription(logoContentDescription).assertDoesNotExist()
        onNode(hasClickAction()).run {
            isDisplayed()
            performClick()
            isDisplayed()
        }
        onNode(hasContentDescription(logoContentDescription)).isDisplayed()
    }
}
