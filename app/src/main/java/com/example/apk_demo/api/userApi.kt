import io.ktor.client.call.body
import io.ktor.client.request.HttpResponseData
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.StructureKind
import java.util.Objects

@Serializable
data class UserInfo(
    val name: String = "",
    val age: Int = 0,
    val avatar: String = ""
)

enum class LoginMethod(val value: String) {
    UNAME("uname")
}

@Serializable
data class LoginParams(
    val loginMethod: LoginMethod,
    val payload: LoginPayload
)

@Serializable
data class LoginPayload(val username: String, val password: String)

object userApi{
    //获取用户信息
    suspend fun getUserInfo() = Api.ktorClient.get("/userInfo").body<HttpRes<UserInfo>>().data
    // 登录
    suspend fun login(params: LoginParams): String? {
        val token = Api.ktorClient.post("/login") {
            setBody(params)
        }.body<HttpRes<String>>().data
        Api.authToken = token
        return token
    }
}
