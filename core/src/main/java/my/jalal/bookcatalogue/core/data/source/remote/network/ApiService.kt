package my.jalal.bookcatalogue.core.data.source.remote.network

import my.jalal.bookcatalogue.core.data.source.remote.response.ListBookResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("volumes")
    suspend fun getBooksBySearching(
        @Query("q") keywords: String?
    ): ListBookResponse
}