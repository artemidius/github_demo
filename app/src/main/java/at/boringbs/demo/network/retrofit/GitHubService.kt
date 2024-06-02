package at.boringbs.demo.network.retrofit

import at.boringbs.demo.data.model.GithubUser
import at.boringbs.demo.data.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface GitHubService {
    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") user: String,
        @Query("page") page: String,
        @Query("per_page") perPage: String,
    ): SearchResponse

    @GET("users/{id}")
    suspend fun getUser(
        @Path("id") id: String
    ): GithubUser
}
