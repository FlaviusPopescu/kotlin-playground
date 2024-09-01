import api.RocketApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlin.time.Duration.Companion.seconds

class GetGreetings(
    private val rocketApi: RocketApi = RocketApi()
) {
    private val platform = getPlatform()

    private val _greetings = MutableStateFlow<List<String>>(listOf())
    val greetings: StateFlow<List<String>> get() = _greetings

    suspend fun refresh() {
        greetings().collect { newGreeting ->
            _greetings.update { it + newGreeting }
        }
    }

    private fun greetings(): Flow<String> = flow {
        emit("Hello, ${platform.name}!")
        delay(1.seconds)
        emit(rocketApi.launchStatus())
    }
}
