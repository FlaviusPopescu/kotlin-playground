package dev.flavius.playground.multiplatform

import App
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "kotlin-multiplatform",
        state = rememberWindowState(size = remember { DpSize(800.dp, 800.dp) })
    ) {
        App()
    }
}
