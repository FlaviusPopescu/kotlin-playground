package api

import api.data.RocketLaunch
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.json.Json
import co.touchlab.kermit.Logger.Companion as KermitLogger

const val BASE_URL = "https://api.spacexdata.com/v4/launches"

class RocketApi {
    private val httpClient = HttpClient {
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    KermitLogger.i { message }
                }
            }
        }
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun launchStatus(): String =
        try {
            "The last successful rocket launch was on ${getDateOfLastSuccessfulLaunch()} 🚀"
        } catch (e: Exception) {
            KermitLogger.e(e) { "Failed fetching launch information from spacexdata" }
            ""
        }

    private suspend fun getDateOfLastSuccessfulLaunch(): String {
        val launches: List<RocketLaunch> = httpClient.get(BASE_URL).body()
        val lastSuccessfulLaunch = launches.last { it.launchSuccess == true }
        val date = Instant.parse(lastSuccessfulLaunch.launchDateUtc)
            .toLocalDateTime(TimeZone.currentSystemDefault())
        return "${date.month} ${date.date}, ${date.year}"
    }
}
