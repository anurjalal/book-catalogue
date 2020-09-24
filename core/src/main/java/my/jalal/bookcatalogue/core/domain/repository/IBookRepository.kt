package my.jalal.bookcatalogue.core.domain.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import my.jalal.bookcatalogue.core.data.Resource
import my.jalal.bookcatalogue.core.domain.model.Book

interface IBookRepository {
    suspend fun getBookBySearching(keyword: String?): LiveData<Resource<List<Book>>>

    fun getFavoriteBook(): Flow<List<Book>>

    fun setFavoriteBook(book: Book, state: Boolean)

    fun isBookFavorite(id: String): Flow<Int>
}