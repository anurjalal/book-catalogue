package my.jalal.bookcatalogue.core.domain.usecase

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import my.jalal.bookcatalogue.core.data.Resource
import my.jalal.bookcatalogue.core.domain.model.Book

interface BookUseCase {

    suspend fun getBookBySearching(keyword: String?): LiveData<Resource<List<Book>>>
    fun getFavoriteBook(): Flow<List<Book>>
    fun isBookFavorite(id: String): Flow<Int>
    fun setFavoriteBook(book: Book, state: Boolean)
}