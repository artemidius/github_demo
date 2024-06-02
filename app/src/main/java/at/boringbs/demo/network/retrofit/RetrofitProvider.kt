package at.boringbs.demo.network.retrofit

import at.boringbs.demo.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class RetrofitProvider {
    companion object {
        private const val token = BuildConfig.GithubKey

        private val httpClient = OkHttpClient.Builder().apply {
            addInterceptor(Interceptor { chain ->
                with(chain.request()) {
                    val request = newBuilder()
                        .header("Authorization", "Bearer $token")
                        .method(method, body)
                        .build()
                    chain.proceed(request)
                }
            })
        }.build()

        private const val baseUrl = "https://api.github.com/"
        private val networkJson = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType())).apply {
                    if (token.isNotEmpty()) client(httpClient)
                }
                .build()
        }
    }

    val retrofitService: GitHubService by lazy { retrofit.create(GitHubService::class.java) }
}
