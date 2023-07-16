import io.ktor.client.call.body
import io.ktor.client.request.HttpResponseData
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val name: String = "",
    val age: Int = 0,
    val avatar: String = ""
)

class userApi{
    suspend fun getUserInfo(): UserInfo = ktorClient.get("http://www.yankc.com:7001/userInfo").body<HttpRes<UserInfo>>().data
}

val api = userApi()

val test = object {
    val name = "小明"
}

