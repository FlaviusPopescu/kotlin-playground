import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import kotlin_playground.app.generated.resources.Res
import kotlin_playground.app.generated.resources.button_content_description
import kotlin_playground.app.generated.resources.compose_multiplatform
import kotlin_playground.app.generated.resources.logo_content_description
import org.jetbrains.compose.resources.painterResource

@Composable
fun App(modifier: Modifier = Modifier) {
    var showContent by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ShowContentButton(onClick = { showContent = !showContent })
            AnimatedVisibility(showContent) {
                GreetingsWithLogo()
            }
        }
    }
}

@Composable
fun ShowContentButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier.semantics {
            contentDescription = Res.string.button_content_description.toString()
        }
    ) {
        Text("Click me!")
    }
}

@Composable
fun GreetingsWithLogo(
    getGreetings: GetGreetings = remember { GetGreetings() },
    modifier: Modifier = Modifier,
) {
    val greetings by getGreetings.greetings.collectAsState()
    LaunchedEffect(Unit) {
        getGreetings.refresh()
    }

    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painterResource(Res.drawable.compose_multiplatform),
            Res.string.logo_content_description.toString()
        )
        greetings.forEach {
            Text(it)
            HorizontalDivider()
        }
    }
}
