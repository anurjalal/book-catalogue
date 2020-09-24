package my.jalal.bookcatalogue.core.data.source.remote

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import my.jalal.bookcatalogue.core.data.source.remote.network.ApiResponse
import my.jalal.bookcatalogue.core.data.source.remote.network.ApiService
import my.jalal.bookcatalogue.core.data.source.remote.response.BookResponse
import java.lang.Exception

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getBookBySearching(keyword: String?): ApiResponse<List<BookResponse>> =
        withContext(Dispatchers.IO) {
            try {
                val responseDeffered = async { apiService.getBooksBySearching(keyword) }
                val bookResponse = responseDeffered.await().bookResponse
                ApiResponse(bookResponse, "")
            }
            catch (e: Exception) {
                Log.e("RemoteDataSource", e.toString())
                ApiResponse(null, e.toString())
            }

        }
}

