import android.system.Os.accept
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


val ktorClient = HttpClient(Android) {
    install(Logging) {
        level = LogLevel.ALL
    }

    install(HttpTimeout) {
        requestTimeoutMillis = 15000L
        connectTimeoutMillis = 15000L
        socketTimeoutMillis = 15000L
    }

    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            prettyPrint = true
            isLenient = true
            explicitNulls = false
        })
    }

    // Default request for POST, PUT, DELETE,etc...
    install(DefaultRequest) {
        header(HttpHeaders.ContentType, ContentType.Application.Json)
        //add this accept() for accept Json Body or Raw Json as Request Body
        accept(ContentType.Application.Json)
    }
}

@Serializable
data class HttpRes<T> (
    val code: String,
    val message: String,
    val data: @Serializable T
)
